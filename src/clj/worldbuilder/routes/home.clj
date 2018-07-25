(ns worldbuilder.routes.home
  (:require [worldbuilder.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer :all]
            [worldbuilder.db.core :as db]
            [clojure.java.io :as io]))


(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))


(defroutes home-routes
  (GET "/" [] (home-page)))
