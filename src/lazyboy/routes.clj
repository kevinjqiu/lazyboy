(ns lazyboy.routes
  (:use [lazyboy.core :only [b]]
        [clojure.data.json :only (json-str)]
        [noir.core :only (defpage)])
  (:require [lazyboy.netflix :as netflix]))

(defpage [:post "/netflix/start"] {:keys [username password]}
  (netflix/login b username password)
  "ok")

(defpage [:get "/netflix/genres"] {}
  (json-str (netflix/genres b)))

(defpage [:get "/netflix/movies"] {}
  (json-str (netflix/movies b)))
