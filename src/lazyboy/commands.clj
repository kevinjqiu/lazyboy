(ns lazyboy.commands
  (:use [lazyboy.robot :only (mouse-move)]))

(def command-map (atom {}))

(defn get-command [command-str]
  ((keyword command-str) @command-map))

(defn add-command [command-keyword handler]
  (swap! command-map assoc command-keyword handler))

(defn dispatch [command args]
  (let [command-handler (get-command command)]
    (command-handler args)))

(defn- mouse-move-handler [args]
  (let [x (Integer. (:x args))
        y (Integer. (:y args))]
    (do
      (mouse-move x y)
      {:response "done"})))

; TODO: macro version
;(defhandler :mouse-move
;  [args]
;  (let [x (Integer. (:x args))
;        y (Integer. (:y args))]
;    (do
;      (mouse-move x y)
;      {:response "done"})))

(add-command :mouse-move mouse-move-handler)
