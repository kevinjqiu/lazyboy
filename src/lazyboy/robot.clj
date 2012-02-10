(ns lazyboy.robot
  (:import [java.awt Robot]
           [java.awt.event InputEvent]))

(def robot (Robot.))

(defn mouse-move [x y]
  (.mouseMove robot x y))

(defn mouse-left-click []
  (.mousePress robot InputEvent/BUTTON1_MASK))

(defn mouse-right-click []
  (.mousePress robot InputEvent/BUTTON3_MASK))
