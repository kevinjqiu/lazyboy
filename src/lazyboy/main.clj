(ns lazyboy.main
  (:use [clojure.java.io :only (reader file)]
        [clojure.tools.cli]
        [clojure.data.json :only (read-json)]
        [lazyboy.routes])
  (:require [noir.server :as server]))

(def options (ref {}))

(defn- parse-args [args]
  (cli args
       ["-f" "--config-file"]))

(defn -main [& args]
  (let [parsed-opts (first (parse-args args))]
    (dosync
      (ref-set options (read-json (reader (:config-file parsed-opts)))))
    (server/start (:port @options))))
