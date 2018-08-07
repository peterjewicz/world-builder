(ns worldbuilder.db.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]
              [cheshire.core :refer :all]
              [mount.core :refer [defstate]]
              [buddy.hashers :as hashers]
              [worldbuilder.db.user :as db-user]
              [worldbuilder.config :refer [env]])
              (:import org.bson.types.ObjectId))

(defstate db*
  :start (-> env :database-url mg/connect-via-uri)
  :stop (-> db* :conn mg/disconnect))

(defstate db
  :start (:db db*))

(defn get-user-count-username
  [username]
  (mc/count db "user" {:username username}))

(defn get-user-count-email
  [email]
  (mc/count db "user" {:email email}))

(defn get-user-by-username [username]
  (def user (mc/find-one-as-map db "user" {:username username}))
  (assoc user :_id (str (user :_id))))

(defn create-user
  "Creates a new user with a token"
  [username email password]
  (let [user {:username username
              :email email
              :password (hashers/derive password)
              :token (str (java.util.UUID/randomUUID))
              }]
  (mc/insert db "user" user)
  {:body (:token user)}))

(defn generate-new-token [username]
  (let
    [current-user (mc/update db "user" {:username username} {$set {:token (str (java.util.UUID/randomUUID))}} {:upsert true})]
    (:token (get-user-by-username username)))
)


;TODO we have to coerce vlaue into its own map
;TODO pass userID
(defn create-entity [type, value]
  (let
    [item (parse-string value true)]
    (mc/insert db type {:user_id "00000000" :value item}))
    "Entity Inserted")

(defn find-entity-by-id []
    (def characters (mc/find-maps db "characters" {:user_id "5b4403d3c1025107593fa0b4" }))
  (map ; Turn characters into a modified list
    #(update % :_id str) ; By updating each map :id by casting to a string
    characters))

;gets all the entities by type for a given user id
(defn get-entity-by-type [type id])

;gets a specific entity of type && id
(defn get-entity-by-id [type id])

(defn create-new-world [name id]
  (mc/insert db "worlds" {:user_id "5b4403d3c1025107593fa0b4" :name "world3"}))
;
(defn get-worlds-by-id [id]
  (let [worlds (mc/find-maps db "worlds" {:user_id "5b4403d3c1025107593fa0b4" })]
        (if (not-empty worlds)
          (map ; Turn characters into a modified list
            #(update % :_id str) ; By updating each map :id by casting to a string
            worlds)
          (println "No Worlds"))))

;********USER SPECIFIC DB OPERATIONS*********

(defn get-user-by-id [id]
  (def user (mc/find-one-as-map db "worldbuilder" {:_id (ObjectId. id) }))
  (assoc user :_id (str (test :_id))))


  ; (defn update-user [id first-name last-name email]
  ;   (mc/update db "users" {:_id id}
  ;              {$set {:first_name first-name
  ;                     :last_name last-name
  ;                     :email email}}))
