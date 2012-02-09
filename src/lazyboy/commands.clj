(ns lazyboy.commands)

(declare command-map)

(defn dispatch [command args]
  (let [command-handler ((keyword command) command-map)]
    (command-handler args)))

(defn- mouse-move [args]
  (let [x (:x args)
        y (:y args)]
    {:response {:command "mouse-move" :x x :y y}}))

(def command-map 
  {:mouse-move mouse-move})
