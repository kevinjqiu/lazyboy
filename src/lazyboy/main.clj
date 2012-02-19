(ns lazyboy.main
  (:use [clojure.tools.cli]
        [clojure.tools.logging :only (info)]
        [clojure.data.json :only (write-json)]
        [lazyboy.request :only (request-factory dispatch)]
        [server.socket])
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [lazyboy.commands :as cmds])
  (:import [java.io PrintWriter]))

(defn- parse-args [args]
  (cli args
    ["-p" "--port" :parse-fn #(Integer. %) :default 5000]))

(defn handler [in out]
  (let [r (io/reader in)
        w (PrintWriter. (io/writer out))]
    (loop []
      (try
        (let [raw-input (.readLine r)
              request (request-factory raw-input)
              response (dispatch request)]
          (.write w "ok")
          (.flush w))
        (catch Exception e (println e)))
      (recur))))

(defn -main [& args]
  (let [opts (first (parse-args args))]
    (create-server (:port opts) handler)
    (info "Server started")))
