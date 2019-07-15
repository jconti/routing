(ns user
  (:require
   [clj-http.client :as http]
   [clojure.tools.namespace.repl :refer [refresh refresh-all]]
   [routing.init :as init]))

(def config {})

(defn go [] (init/init! config))
(defn stop [] (init/shutdown!))
(defn reset []
  (init/shutdown!)
  (refresh :after 'user/go))
