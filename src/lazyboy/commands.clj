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

(defmacro defcmd [cmd-keyword args & body]
  `(let [handler-name# (name ~cmd-keyword)]
     (defn handler-name# ~args ~@body)
     (add-command ~cmd-keyword (var handler-name#))))

(defcmd :mouse-move [args]
  (let [x (Integer. (:x args))
        y (Integer. (:y args))]
    (do
      (mouse-move x y)
      {:response "done"})))

(defcmd :mouse-down [args]
  (let [x (Integer. (:x args))
        y (Integer. (:y args))]
    (do
      (mouse-down x y)
      {:response "done"})))

(defcmd :mouse-left-click [args]
  (mouse-left-click)
  {:response "done"})

(defcmd :mouse-right-click [args]
  (mouse-right-click)
  {:response "done"})
