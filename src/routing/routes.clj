(ns routing.routes
  (:require
   [cheshire.core :as json]
   [compojure.core :refer :all]
   [compojure.route :as route]))

(defn handler
  [req]
  {:status 200 :body "Hello World!"})

(defn wrap-json
  "If there is a data structure in the body, serialize to JSON."
  [handler]
  (fn [req]
    (let [resp (handler req)]
      (if (coll? (:body resp))
        (update resp :body json/generate-string)
        resp))))

(defroutes app
  (GET "/" [] handler)
  (route/not-found "<h1>Page not found</h1>"))
