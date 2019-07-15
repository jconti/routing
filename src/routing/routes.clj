(ns routing.routes
  (:require
   [cheshire.core :as json]
   [clojure.pprint :as pp]
   [compojure.core :refer :all]
   [compojure.route :as route]
   [ring.util.request :as ring-request]))

(defn hello-world
  [req]
  {:status 200 :body "Hello World!"})

(defn echo
  [req]
  {:status 200 :body req})

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

(defroutes app
  (GET "/" [] hello-world)
  (GET "/echo" [] echo)
  (route/not-found "<h1>Page not found</h1>"))

(def handler (-> app wrap-json wrap-req-body))
