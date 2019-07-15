(ns routing.routes
  (:require
   [cheshire.core :as json]
   [compojure.core :refer :all]
   [compojure.route :as route]))

(defn handler
  [req]
  {:status 200 :body "Hello World!"})

(defroutes app
  (GET "/" [] handler)
  (route/not-found "<h1>Page not found</h1>"))
