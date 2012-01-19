(ns lazyboy.main
  (:use [clojure.java.io :only (reader file)]
        [clojure.tools.cli]
        [clojure.data.json :only (read-json)])
  (:require [lazyboy.plugins.netflix :as netflix]))

(defn- parse-args [args]
  (cli args
       ["-f" "--config-file"]))

(defn -main [& args]
  (let [options (first (parse-args args))
        cfg-file (:config-file options)]
    (with-open [cfg-reader (reader (:config-file options))]
      (let [netflix-option (read-json cfg-reader)]
        (binding [netflix/*username* (:username netflix-option)
                  netflix/*password* (:password netflix-option)]
          (netflix/login))))))
