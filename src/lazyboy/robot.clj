(ns lazyboy.robot
  (:import [java.awt Robot]))

(def robot (Robot.))

(defn mouse-move [x y]
  (.mouseMove robot x y))
