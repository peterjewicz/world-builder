(ns worldbuilder.user.create
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [worldbuilder.db.core :as db]
            [monger.collection :as mc]
            [buddy.hashers :as hashers]
            [monger.operators :refer :all]
            [buddy.auth :refer [authenticated?]])
            (:import org.bson.types.ObjectId))

;TODO we need to change this namespace to user.functions or something as it does more than just create
(defn create
  "Checks to make sure the password nad Username are unique"
  [username email password]
  (if (and (= 0 (db/get-user-count-username username)) (= 0 (db/get-user-count-email email)))
    (db/create-user username email password)
    {:body "The Username Or Email Is already taken" :error "true"}))

(defn login
  "Checks a users login credentials and generates/returns a new token"
  [username password]
  (let [current-user (db/get-user-by-username username)]
    (if (and current-user (hashers/check password (:password current-user)))
      {:body "success" :token (db/generate-new-token username) :email (:email current-user)}
      {:body "Your Username Or Password is Incorrect"}
    )))
