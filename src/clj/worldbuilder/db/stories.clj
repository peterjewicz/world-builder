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
      [entity (mc/insert-and-return db "stories" {:user_id userId :name name :worldId worldId :stories []})]
      (assoc entity :_id (str (entity :_id)))))

