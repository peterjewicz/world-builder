(ns worldbuilder.db.entities
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [cheshire.core :refer :all]
            [mount.core :refer [defstate]]
            [worldbuilder.db.core :refer [db]]
            [worldbuilder.config :refer [env]]
            [monger.operators :refer :all]
            [worldbuilder.db.stories :as stories])
            (:import org.bson.types.ObjectId))
;TODO we have to coerce vlaue into its own map
;TODO pass userID
;TODO We need to make this a bit nicer - break udate into its own function
(defn create-entity [type, value, worldId, userId, currentId]
  (if (clojure.string/blank? currentId)
    (let
      [item (parse-string value true)
      entity (mc/insert-and-return db type {:user_id userId :value item :worldId worldId})]
      (assoc entity :_id (str (entity :_id))))
    (let
      [item (parse-string value true)]
      (mc/update-by-id db type (ObjectId. currentId) {$set {:value item}} {:multi false})
      "Entity Updated")))

(defn get-entity-by-world
  "Gets all entities of 'entityType' for world 'worldId'"
  [entityType worldId]
  (let [entities (mc/find-maps db entityType {:worldId worldId})]
    (map ; Turn characters into a modified list
      #(update % :_id str) ; By updating each map :id by casting to a string
      entities)))

(defn get-all-entities
  "Grabs all the entities for a user for a given world"
  [worldId]
  (let [entities {}]
    (assoc entities :character (get-entity-by-world "character" worldId)
                    :creature (get-entity-by-world "creature" worldId)
                    :region (get-entity-by-world "region" worldId)
                    :city (get-entity-by-world "city" worldId)
                    :pointofinterest (get-entity-by-world "pointofinterest" worldId)
                    :religion (get-entity-by-world "religion" worldId)
                    :language (get-entity-by-world "language" worldId)
                    :spell (get-entity-by-world "spell" worldId)
                    :item (get-entity-by-world "item" worldId)
                    :planet (get-entity-by-world "planet" worldId)
                    :technology (get-entity-by-world "technology" worldId)
                    :stories (stories/get-stories-by-world worldId))))
