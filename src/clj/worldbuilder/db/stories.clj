(ns worldbuilder.db.stories
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [cheshire.core :refer :all]
            [mount.core :refer [defstate]]
            [worldbuilder.db.core :refer [db]]
            [worldbuilder.config :refer [env]]
            [monger.operators :refer :all])
            (:import org.bson.types.ObjectId))
;TODO we have to coerce vlaue into its own map
;TODO pass userID
;TODO We need to make this a bit nicer - break udate into its own function
(defn create-story [name, worldId, userId]
  (let
      [entity (mc/insert-and-return db "stories" {:user_id userId :name name :worldId worldId :stories ""})]
      (assoc entity :_id (str (entity :_id)))))

(defn get-stories-by-world
  "Gets all entities of 'entityType' for world 'worldId'"
  [worldId]
  (let [stories (mc/find-maps db "stories" {:worldId worldId})]
    (map ; Turn characters into a modified list
      #(update % :_id str) ; By updating each map :id by casting to a string
      stories)))

(defn save-story-details [storyId values]
  "saves the details for a story"
(mc/update db "stories" {:_id (ObjectId. storyId)}
  {$set {:stories values}})
(:body "200")) ; we can just return this string since the UI is our soruce of truth here

