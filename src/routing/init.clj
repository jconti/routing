(ns routing.init
  (:require
   [ring.adapter.jetty :as jetty]
   [routing.global :as global]
   [routing.routes :as routes]))

(defn init!
  [config]
  ; jetty web server with a handler for all requests
  (let [web-server (jetty/run-jetty
                    routes/app
                    {:port 8080 :join? false})]
    (reset! global/state web-server)))

(defn shutdown! []
  (when-let [web-server @global/state]
    (.stop web-server)
    (reset! global/state nil)))
