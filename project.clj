(defproject routing "0.1.0-SNAPSHOT"
  :description "A project to show web server routing."
  :url "http://example.com/FIXME"

  :dependencies
  [[org.clojure/clojure "1.10.0"]
   [compojure "1.6.1"]
   [ring "1.7.1"]
   [cheshire "5.8.1"]]

  :main ^:skip-aot routing.core
  :target-path "target/%s"

  :profiles
  {:uberjar
   {:aot :all}
   :dev
   {:dependencies [[javax.servlet/servlet-api "2.5"]
                   [org.clojure/tools.namespace "0.2.11"]
                   [clj-http "3.9.1"]]
    :source-paths ["dev"]}})
