(ns worldbuilder.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[worldbuilder started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[worldbuilder has shut down successfully]=-"))
   :middleware identity})
