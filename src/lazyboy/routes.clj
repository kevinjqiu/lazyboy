(ns lazyboy.routes
  (:use [lazyboy.core :only [b]]
        [noir.core :only (defpage)])
  (:require [lazyboy.netflix :as netflix]
            [noir.response :as response]))

(defpage [:post "/netflix/start"] {:keys [username password]}
  (netflix/login b username password)
  "ok")

(defpage [:get "/netflix/genres"] {}
  (response/json (netflix/genres b)))

(defpage [:get "/netflix/movies"] {}
  (response/json (netflix/movies b)))

