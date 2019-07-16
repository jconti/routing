(ns routing.routes
  (:require
   [cheshire.core :as json]
   [clojure.pprint :as pp]
   [reitit.ring :as r]
   [ring.util.request :as ring-request]))

(defn hello-world
  [req]
  {:status 200 :body "Hello World!"})

(defn echo
  [req]
  {:status 200 :body (dissoc req :reitit.core/router :reitit.core/match)})

(defn wrap-req-body
  [handler]
  (fn [req]
    (let [body (ring-request/body-string req)]
      (handler (assoc req :body body)))))

(defn wrap-json
  "If there is a data structure in the body, serialize to JSON."
  [handler]
  (fn [req]
    (let [resp (handler req)]
      (if (coll? (:body resp))
        (update resp :body json/generate-string)
        resp))))

(def app
  (r/ring-handler
   (r/router
    [["/" {:get hello-world}]
     ["/echo" {:get echo}]]
    {:data {:middleware [wrap-req-body wrap-json]}})
   (constantly {:status 404 :body "Ouch!"})))

(def handler app)
