(ns lazyboy.main
  (:use [clojure.tools.cli]
        ;TODO: add logging
        ;[clojure.tools.logging]
        [clojure.java.io]
        [clojure.data.json :only (read-json write-json)]
        [server.socket])
  (:require [clojure.string :as string]
            [lazyboy.commands :as cmds])
  (:import [java.io PrintWriter]))

(defn- parse-args [args]
  (cli args
    ["-p" "--port" :parse-fn #(Integer. %) :default 5000]))

(defn handler [in out]
  (let [r (reader in)
        w (PrintWriter. (writer out))]
    (loop []
      (let [raw-input (.readLine r)
            request (read-json raw-input)
            response (cmds/dispatch (:command request) (:args request))]
        (write-json response w true)
        (.flush w))
      (recur))))

(defn -main [& args]
  (let [opts (first (parse-args args))]
    (create-server (:port opts) handler)))
