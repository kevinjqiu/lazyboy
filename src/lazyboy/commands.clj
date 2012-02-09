(ns lazyboy.commands)

;(declare command-map)
(def command-map (atom {}))

(defn get-command [command-str]
  ((keyword command-str) @command-map))

(defn add-command [command-keyword handler]
  (swap! command-map assoc command-keyword handler))

(defn dispatch [command args]
  (let [command-handler (get-command command)]
    (command-handler args)))

(defn- mouse-move [args]
  (let [x (:x args)
        y (:y args)]
    {:response {:command "mouse-move" :x x :y y}}))

(add-command :mouse-move mouse-move)

;(def command-map 
;  {:mouse-move mouse-move})
