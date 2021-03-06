(ns worldbuilder.middleware.auth
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
            (:import org.bson.types.ObjectId))


(defn check-user-auth
  "Checks a given token against users to see if it's a real user"
  [handler]
  (fn [request]
    (let
      [token (get (:headers request) "token")
       user (db/get-user-by-token token)]
      (if user
        (handler (assoc request :user user))     ; pass to wrapped handler
        (unauthorized {:error "unauthorized"})))))


; TODO we need to wrap the user check to make sure that a valid world is return, throws a weird error if it isnt
(defn check-world-auth
  "Checks an operation being performed on the world it's being performed on
  If the logged in user owns the world it's good, if not return unauthorized"
  [handler]
  (fn [request]
    (let [worldId (get (:route-params request) :worldId)
          world (db/get-world-by-id worldId)]
          (if (= (:user_id world) (:_id (:user request)))
          (handler (assoc request :world world))
          (unauthorized {:error "unauthorized"})))))

(defn check-user-world-count
  "Cheks if a user is a subscriber, if not, stops them from adding more than one world
  TODO might want to rewrite the logic a bit to remove redudant handler - seems to always fire
  if place at end even if the unauthorized section fires first"
  [handler]
  (fn [request]
    (let
      [token (get (:headers request) "token")
       user (db/get-user-by-token token)
       worlds (db/get-worlds-by-id (:_id user))]
       (if (empty? (:stripeToken user))
        (do
          (if (> (count worlds) 0)
            (unauthorized {:error "Subscribe To Create More Worlds"})
            (handler request)))
            (handler request)))))
