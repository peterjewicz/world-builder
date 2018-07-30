(ns worldbuilder.routes.services
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [cheshire.core :refer :all]
            [worldbuilder.db.core :as db]
            [compojure.api.meta :refer [restructure-param]]
            [buddy.auth.accessrules :refer [restrict]]
            [worldbuilder.user.create :as user]
            [buddy.auth :refer [authenticated?]])
            (:import org.bson.types.ObjectId))

          ; (def Activity {
          ;     :_id s/Str
          ;     :first_name String })
          ;
          ;   (def Activities [Activity])
          (def cors-headers
            { "Access-Control-Allow-Origin" "*"
              "Access-Control-Allow-Headers" "Content-Type"
              "Access-Control-Allow-Credentials" "true"
              "Access-Control-Allow-Methods" "GET,POST,OPTIONS" })

          (defn all-cors
            "Allow requests from all origins"
            [handler]
            (fn [request]
              (let [response (handler request)]
                (update-in response [:headers]
                  merge cors-headers ))))


(defn access-error [_ _]
  (unauthorized {:error "unauthorized"}))

(defn wrap-restricted [handler rule]
  (restrict handler {:handler  rule
                     :on-error access-error}))

(defn wrap-api-auth [handler]
  (fn [request]
    (if (= 2 4)
      (handler request)     ; pass to wrapped handler
      (unauthorized {:error "unauthorized"}))))

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


    (GET "/entity/:type/" []
      :path-params [type :- String]
      :summary     "Gets all entities of type by user `id`"
      (ok (db/get-entity-by-type type)))


    (GET "/entity/:type/:id" []
      :path-params [type :- String, id :- String]
      :summary     "Gets a specific entity by its `id`"
      (ok (db/get-entity-by-id type id)))

    ; TODO attach Middleware route here to check auth
    (POST "/entity" []
      :body-params [type :- String, values :- String]
      ; :header-params [authToken :- String]
      ; (ok {:body values}))
      (ok (db/create-entity type values)))

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
