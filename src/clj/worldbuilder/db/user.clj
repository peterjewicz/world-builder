(ns worldbuilder.db.user
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]
              [cheshire.core :refer :all]
              [mount.core :refer [defstate]]
              [buddy.hashers :as hashers]
              [worldbuilder.config :refer [env]])
              (:import org.bson.types.ObjectId))


; (defn get-user-count-username
;   [username]
;   (mc/count db "user" {:username username}))
;
; (defn get-user-count-email
;   [email]
;   (mc/count db "user" {:email email}))
;
; (defn get-user-by-username [username]
;   (def user (mc/find-one-as-map db "user" {:username username}))
;   (assoc user :_id (str (user :_id))))
