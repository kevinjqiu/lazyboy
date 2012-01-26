(ns lazyboy.main
  (:use [clojure.java.io :only (reader file)]
        [clojure.tools.cli]
        [clojure.data.json :only (read-json json-str)]
        [noir.core :only (defpage)])
  (:require [lazyboy.netflix :as netflix]
            [noir.server :as server]))

(def options (ref {}))

(defn- parse-args [args]
  (cli args
       ["-f" "--config-file"]))

(defpage [:post "/netflix/start"] []
  (netflix/login (:username @options) (:password @options)))

(defpage [:get "/netflix/genres"] []
  (json-str (netflix/genres)))

(defpage [:get "/netflix/movies"] []
  (json-str (netflix/movies)))

(defn -main [& args]
  (let [parsed-opts (first (parse-args args))]
    (dosync
      (ref-set options (read-json (reader (:config-file parsed-opts)))))
    (server/start 9999)))
