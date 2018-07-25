(ns worldbuilder.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [worldbuilder.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[worldbuilder started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[worldbuilder has shut down successfully]=-"))
   :middleware wrap-dev})
