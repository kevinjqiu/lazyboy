(ns lazyboy.main
  (:require [lazyboy.plugins.netflix :as netflix]))

(defn -main [& args]
  (netflix/login))
