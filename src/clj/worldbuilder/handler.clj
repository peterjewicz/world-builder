(ns worldbuilder.handler
  (:require
            [worldbuilder.layout :refer [error-page]]
            [worldbuilder.routes.services :refer [service-routes]]
            [compojure.core :refer [routes wrap-routes]]
            [ring.util.http-response :as response]
            [worldbuilder.middleware :as middleware]
            [compojure.route :as route]
            [ring.middleware.cors :refer [wrap-cors]]
            [worldbuilder.env :refer [defaults]]
            [mount.core :as mount]))

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

(mount/defstate init-app
  :start ((or (:init defaults) identity))
  :stop  ((or (:stop defaults) identity)))

(mount/defstate app
  :start
  (middleware/wrap-base
    (routes
      (all-cors service-routes)
      (route/not-found
        (:body
        (error-page {:status 404
          :title "page not found"}))))))
