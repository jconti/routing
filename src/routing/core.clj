(ns routing.core
  (:require
   [routing.init :as init])
  (:gen-class))

(defn -main
  [& _]
  (init/init!))
