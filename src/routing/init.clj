(ns routing.init
  (:require
   [ring.adapter.jetty :as jetty]
   [routing.global :as global]))

(defn init!
  [config]
  ; jetty web server with a handler for all requests
  (let [web-server (jetty/run-jetty
                    (fn [req] {:status 200 :body "Hello World!"})
                    {:port 8080 :join? false})]
    (reset! global/state web-server)))

(defn shutdown! []
  (when-let [web-server @global/state]
    (.stop web-server)
    (reset! global/state nil)))
