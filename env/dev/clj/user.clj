(ns user
  (:require [worldbuilder.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [worldbuilder.core :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'worldbuilder.core/repl-server))

(defn stop []
  (mount/stop-except #'worldbuilder.core/repl-server))

(defn restart []
  (stop)
  (start))


