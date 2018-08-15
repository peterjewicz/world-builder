(ns worldbuilder.db.world
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [mount.core :refer [defstate]]
            [worldbuilder.db.core :refer [db]]
            [worldbuilder.config :refer [env]]
            [monger.operators :refer :all])
            (:import org.bson.types.ObjectId))

; TODO move all world queries into this file and make changes to services routes
