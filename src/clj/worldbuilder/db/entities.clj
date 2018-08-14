(ns worldbuilder.db.entities
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [mount.core :refer [defstate]]
            [worldbuilder.db.core :refer [db]]
            [worldbuilder.config :refer [env]]
            [monger.operators :refer :all])
            (:import org.bson.types.ObjectId))

(defn get-entity-by-world
  "Gets all entities of 'entityType' for world 'worldId'"
  [entityType worldId]
  (let [entities (mc/find-maps db entityType {:worldId worldId})]
    (map ; Turn characters into a modified list
      #(update % :_id str) ; By updating each map :id by casting to a string
      entities)))

(defn get-all-entities
  "Grabs all the entities for a user"
  [worldId]
  (let [entities {}]
    (assoc entities :character (get-entity-by-world "character" worldId)
                    :creature (get-entity-by-world "creature" worldId)
                    :region (get-entity-by-world "region" worldId)
                    :city (get-entity-by-world "city" worldId)
                    :pointofinterest (get-entity-by-world "poinrtofinterest" worldId)
                    :religion (get-entity-by-world "religion" worldId)
                    :language (get-entity-by-world "language" worldId)
                    :spell (get-entity-by-world "spell" worldId)
                    :item (get-entity-by-world "item" worldId))))
