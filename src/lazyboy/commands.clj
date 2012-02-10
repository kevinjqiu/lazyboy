(ns lazyboy.commands
  (:use [lazyboy.robot :only (mouse-move
                              mouse-left-click
                              mouse-right-click)]))

(defonce command-map (atom {}))

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

(defn- mouse-left-click-handler [args]
  (mouse-left-click)
  {:response "done"})

(defn- mouse-right-click-handler [args]
  (mouse-right-click)
  {:response "done"})

; TODO: macro version

(add-command :mouse-move mouse-move-handler)
(add-command :mouse-left-click mouse-left-click-handler)
(add-command :mouse-right-click mouse-right-click-handler)
