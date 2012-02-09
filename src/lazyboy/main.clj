(ns lazyboy.main
  (:use [clojure.tools.cli]
        [clojure.java.io]
        [clojure.data.json :only (read-json)]
        [server.socket])
  (:require [clojure.string :as string]))

(defn- parse-args [args]
  (cli args
    ["-p" "--port" :parse-fn #(Integer. %) :default 5000]))

(defn handler [in out]
  (let [r (reader in)
        w (writer out)]
    (loop []
      (let [input (.readLine r)
            request (read-json input)]
        (doto w
          (.write input)
          (.newLine)
          (.write (str request))
          (.newLine)
          (.flush)))
      (recur))))

(defn -main [& args]
  (let [opts (first (parse-args args))]
    (create-server (:port opts) handler)))
