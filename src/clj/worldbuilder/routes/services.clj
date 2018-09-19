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
            [worldbuilder.middleware.auth :as auth-middleware]
            [worldbuilder.config :refer [env]]
            [worldbuilder.billing :as billing]
            [buddy.auth :refer [authenticated?]])
            (:import org.bson.types.ObjectId)
            (:use [amazonica.aws.s3]))


(defn access-error [_ _]
  (unauthorized {:error "unauthorized"}))

(defn wrap-restricted [handler rule]
  (restrict handler {:handler  rule
                     :on-error access-error}))

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

  (GET "/testbilling" []
    (ok {:body (billing/get-stripe-plans)}))

  (GET "/:id/worlds/" request
    :path-params [id :- String]
    :summary     "Gets all the worlds related to a specific 'id'"
    :middleware [auth-middleware/check-user-auth]
    (ok {:body (db/get-worlds-by-id (:_id (:user request)))}))

  (GET "/worlds/:worldId/images" request
    :path-params [worldId :- String]
    :header-params [token :- String]
    :summary     "Gets all the images for the world supplied by 'id'"
    :middleware [auth-middleware/check-user-auth auth-middleware/check-world-auth]
    (ok {:body (list-objects-v2 (:s3creds env)
                {:bucket-name "worldbuilder-twc"
                 :prefix worldId})}))

  ; TODO we need to add middleware here to check image uploader
  (POST "/uploads" []
    :multipart-params [myFile :- s/Any, worldId :- s/Any]
    :summary     "Uploads an image file for a specific world"
    :middleware [upload/wrap-multipart-params]
    (ok {:body (put-object (:s3creds env)
          :bucket-name "worldbuilder-twc"
          :key (str worldId "/" (:filename myFile))
          :file (myFile :tempfile))}))

  (POST "/worlds" request
    :body-params [name :- String]
    :header-params [token :- String]
    :middleware [auth-middleware/check-user-auth]
    :summary "Creates a new world with 'name'"
    (ok (db/create-new-world name (:_id (:user request)))))

    (GET "/entity/:type/" []
      :path-params [type :- String]
      :summary     "Gets all entities of type by user `id`"
      (ok (db/get-entity-by-type type)))

    ; TODO we need a check here to only allow access to entities a user owns
    (GET "/entity/:type/:id" []
      :path-params [type :- String, id :- String]
      :summary     "Gets a specific entity by its `id`"
      (ok (db/get-entity-by-id type id)))

    (GET "/worlds/:worldId/entities" request
      :path-params [worldId :- String]
      :header-params [token :- String]
      :middleware [auth-middleware/check-user-auth auth-middleware/check-world-auth]
      :summary     "Gets all the entities associated with a world"
      (ok (entities/get-all-entities worldId)))

    (POST "/entity/:worldId" request
      :path-params [worldId :- String]
      :body-params [type :- String,
                    values :- String,
                    currentId :- String]
      :header-params [token :- String]
      :middleware [auth-middleware/check-user-auth auth-middleware/check-world-auth]
      (ok (entities/create-entity type values worldId (:_id (:user request)) currentId)))

    (POST "/create-user" []
      :body-params [username :- String, email :- String,  password :- String]
      :summary     "creates a user with a given username/password"
      (ok (user/create username email password)))

    (POST "/login" []
      :body-params [username :- String,  password :- String]
      :summary     "Validates a users login and returns a token"
      (ok (user/login username password)))))
