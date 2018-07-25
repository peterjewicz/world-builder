(ns worldbuilder.db.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]
              [mount.core :refer [defstate]]
              [worldbuilder.config :refer [env]])
              (:import org.bson.types.ObjectId))

(defstate db*
  :start (-> env :database-url mg/connect-via-uri)
  :stop (-> db* :conn mg/disconnect))

(defstate db
  :start (:db db*))

(defn create-user [user]
  (mc/insert db "worldbuilder" user))

(defn create-character [value]
  (mc/insert db "characters" {:user_id "00000000" :value value}))

(defn find-character-by-id2 []
  (def character (mc/find-maps db "characters" {:user_id "5b4403d3c1025107593fa0b4" }))
  (println (first character)))
  ; (assoc character :_id (str (character :_id))))

(defn find-character-by-id []
    (def characters (mc/find-maps db "characters" {:user_id "5b4403d3c1025107593fa0b4" }))
  ; (let [
  ;     character (mc/find-maps db "characters" {:user_id "5b4403d3c1025107593fa0b4" })
  ;   ]
  ;   (doseq [s character]
  ;     (println s)
  ;     )
  ;   )
  (map ; Turn characters into a modified list
    #(update % :_id str) ; By updating each map :value by multiplying it by 2
    characters))

(defn get-entity-by-type [])

(defn get-entity-by-id [])

(defn update-user [id first-name last-name email]
  (mc/update db "users" {:_id id}
             {$set {:first_name first-name
                    :last_name last-name
                    :email email}}))

(defn get-user2 [id]
  (mc/find-one db "worldbuilder" {:_id (ObjectId. id) }))


(defn get-user [id]
  (def test (mc/find-one-as-map db "worldbuilder" {:_id (ObjectId. id) }))
  (assoc test :_id (str (test :_id))))
