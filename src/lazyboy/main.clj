(ns lazyboy.main
  (:use [clojure.tools.cli]
        ;[clojure.tools.logging]
        [clojure.java.io]
        [clojure.data.json :only (read-json write-json)]
        [server.socket])
  (:require [clojure.string :as string]))

(defn- parse-args [args]
  (cli args
    ["-p" "--port" :parse-fn #(Integer. %) :default 5000]))

(defn dispatch [command request]
  {})

(defn handler [in out]
  (let [r (reader in)
        w (writer out)]
    (loop []
      (let [raw-input (.readLine r)
            request (read-json raw-input)
            response (dispatch (:command request) request)]
        ;(write-json response w true))
        (doto w
          (.write raw-input)
          (.newLine)
          (.write (str response))
          (.newLine)
          (.flush)))
      (recur))))

(defn -main [& args]
  (let [opts (first (parse-args args))]
    (create-server (:port opts) handler)))
