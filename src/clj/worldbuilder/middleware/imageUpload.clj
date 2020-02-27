(ns worldbuilder.middleware.imageUpload
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [cheshire.core :refer :all]
            [worldbuilder.db.core :as db]
            [amazonica.aws.s3 :refer :all]
            [worldbuilder.db.entities :as entities]
            [ring.swagger.upload :as upload]
            [compojure.api.meta :refer [restructure-param]]
            [buddy.auth.accessrules :refer [restrict]]
            [worldbuilder.user.create :as user]
            [worldbuilder.config :refer [env]]
            [buddy.auth :refer [authenticated?]])
            (:import org.bson.types.ObjectId))

; Middlewaters that deal with image uploads

; handle max size

; handle total upload capacity


(def free-max-size 2000000000) ; 2gb
(def member-max-size 5000000000) ;5gb - maybe make this more? 

(defn get-image-size-total [images]
  (loop [size 0
         index 0]
    (if (<= (count images) index)
      size
      (recur (+ size (:size (nth images index))) (inc index)))))

; TODO check what it does on null - probably pulls all right???
(defn get-bucket-images [world-id]
  (:object-summaries (list-objects-v2 (:s3creds env) :bucket-name "worldbuilder-twc"
                   :prefix world-id)))


(defn check-user-image-limit
  "Checks to see if a user is saving too many images"
  [handler]
  (fn [request]
    (let [size (get-image-size-total (get-bucket-images (:worldId (:route-params request))))]
      (if (< size free-max-size) ; have to update for memebers
        (handler request)     ; pass to wrapped handler
        (unauthorized {:error "Max image size exceeded"})))))




