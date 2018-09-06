(ns worldbuilder.routes.services
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [cheshire.core :refer :all]
            [worldbuilder.db.core :as db]
            [worldbuilder.db.entities :as entities]
            [ring.swagger.upload :as upload]
            [compojure.api.meta :refer [restructure-param]]
            [buddy.auth.accessrules :refer [restrict]]
            [worldbuilder.user.create :as user]
            [worldbuilder.config :refer [env]]
            [buddy.auth :refer [authenticated?]])
            (:import org.bson.types.ObjectId)
            (:use [amazonica.aws.s3]))




(defn access-error [_ _]
  (unauthorized {:error "unauthorized"}))

(defn wrap-restricted [handler rule]
  (restrict handler {:handler  rule
                     :on-error access-error}))

(defn wrap-api-auth [handler]
  (fn [request]
    (let
      [token (get (:headers request) "token")
       user (db/get-user-by-token token)]
      (if user
        (handler (assoc request :user user))     ; pass to wrapped handler
        (unauthorized {:error "unauthorized"})))))

(defmethod restructure-param :auth-rules
  [_ rule acc]
  (update-in acc [:middleware] conj [wrap-restricted rule]))

(defmethod restructure-param :current-user
  [_ binding acc]
  (update-in acc [:letks] into [binding `(:identity ~'+compojure-api-request+)]))

(defapi service-routes
  {:swagger {:ui "/swagger-ui"
             :spec "/swagger.json"
             :data {:info {:version "1.0.0"
                           :title "Sample API"
                           :description "Sample Services"}}}}

  (GET "/authenticated" []
       :auth-rules authenticated?
       :current-user user
       (ok {:user user}))
  (context "/api" []
    :tags ["worldbuilder"]

    (GET "/plus" []
      :return       Long
      :middleware [wrap-api-auth]
      :query-params [x :- Long, {y :- Long 1}]
      :summary      "x+y with query-parameters. y defaults to 1."
      (ok (+ x y)))

    (POST "/minus" []
      :return      Long
      :body-params [x :- Long, y :- Long]
      :summary     "x-y with body-parameters."
      (ok (- x y)))

    (GET "/times/:x/:y" []
      :return      Long
      :path-params [x :- Long, y :- Long]
      :summary     "x*y with path-parameters"
      (ok (* x y)))


    (GET "/:id/worlds/" request
      :path-params [id :- String]
      :summary     "Gets all the worlds related to a specific 'id'"
      :middleware [wrap-api-auth]
      (ok {:body (db/get-worlds-by-id (:_id (:user request)))}))

    (POST "/uploads" []
      :multipart-params [myFile :- s/Any, worldId :- s/Any]
      :summary     "Gets all the worlds related to a specific 'id'"
      :middleware [upload/wrap-multipart-params]
      ; (ok (slurp (myFile :tempfile))))
     ; (println (:filename myFile)))
      (ok {:body (put-object (:s3creds env)
            :bucket-name "worldbuilder-twc"
            :key (str worldId "/" (:filename myFile))
            :file (myFile :tempfile))}))
      ; (ok {:body "test"}))

    (POST "/worlds" request
      :body-params [name :- String]
      :header-params [token :- String]
      :middleware [wrap-api-auth]
      :summary "Creates a new world with 'name'"
      (ok (db/create-new-world name (:_id (:user request)))))

    (GET "/entity/:type/" []
      :path-params [type :- String]
      :summary     "Gets all entities of type by user `id`"
      (ok (db/get-entity-by-type type)))


    (GET "/entity/:type/:id" []
      :path-params [type :- String, id :- String]
      :summary     "Gets a specific entity by its `id`"
      (ok (db/get-entity-by-id type id)))

    (GET "/worlds/:id/entities" request
      :path-params [id :- String]
      :header-params [token :- String]
      :middleware [wrap-api-auth]
      :summary     "Gets all the entities associated with a world"
      (ok (entities/get-all-entities id)))

    (POST "/entity" request
      :body-params [type :- String,
                    values :- String,
                    worldId :- String
                    currentId :- String]
      :header-params [token :- String]
      :middleware [wrap-api-auth]
      (ok (entities/create-entity type values worldId (:_id (:user request)) currentId)))

    (POST "/divide" []
      :return      Double
      :form-params [x :- Long, y :- Long]
      :summary     "x/y with form-parameters"
      (ok (/ x y)))

    (POST "/create-user" []
      :body-params [username :- String, email :- String,  password :- String]
      :summary     "creates a user with a given username/password"
      (ok (user/create username email password)))


    (POST "/login" []
      :body-params [username :- String,  password :- String]
      :summary     "Validates a users login and returns a token"
      (ok (user/login username password)))



    (GET "/power" []
      :return      Long
      :header-params [x :- Long, y :- Long]
      :summary     "x^y with header-parameters"
      (ok (long (Math/pow x y))))))
