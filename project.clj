(defproject routing "0.1.0-SNAPSHOT"
  :description "A project to show web server routing."
  :url "http://example.com/FIXME"

  :dependencies
  [[org.clojure/clojure "1.10.0"]
   [compojure "1.6.1"]
   [ring "1.7.1"]]

  :main ^:skip-aot routing.core
  :target-path "target/%s"

  :profiles
  {:uberjar
   {:aot :all}
   :dev
   {:source-paths ["dev"]}})
