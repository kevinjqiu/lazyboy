(ns lazyboy.commands
  (:use [lazyboy.robot :only (mouse-move
                              mouse-down
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

(defn- mouse-down-handler [args]
  (let [x (Integer. (:x args))
        y (Integer. (:y args))]
    (do
      (mouse-down x y)
      {:response "done"})))

(defn- mouse-left-click-handler [args]
  (mouse-left-click)
  {:response "done"})

(defn- mouse-right-click-handler [args]
  (mouse-right-click)
  {:response "done"})

; TODO: macro version
(add-command :mouse-move mouse-move-handler)
(add-command :mouse-down mouse-down-handler)
(add-command :mouse-left-click mouse-left-click-handler)
(add-command :mouse-right-click mouse-right-click-handler)

(defmacro defcmd [command-name robot-command]
  (let [handler-symbol (symbol (str command-name "-handler"))
        command-keyword (keyword command-name)]
    `(do
      (defn ~handler-symbol [a] body)
      (add-command ~command-keyword ~handler-symbol))))
(defcmd mouse-move-2 (println "bar"))
