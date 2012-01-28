(ns lazyboy.routes
  (:use [clojure.data.json :only (json-str)]
        [noir.core :only (defpage)])
  (:require [lazyboy.netflix :as netflix]))

(defpage [:post "/netflix/start"] {:keys [username password]}
  (netflix/login username password)
  "")

(defpage [:get "/netflix/genres"] {}
  (json-str (netflix/genres)))

(defpage [:get "/netflix/movies"] {}
  (json-str (netflix/movies)))
