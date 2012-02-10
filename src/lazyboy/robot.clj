(ns lazyboy.robot
  (:import [java.awt Robot MouseInfo]
           [java.awt.event InputEvent]))

(def robot (Robot.))

(def starting-pt (atom {}))

(defn mouse-location []
  (let [pt (.getLocation (MouseInfo/getPointerInfo))]
    {:x (.getX pt)
     :y (.getY pt)}))

(defn mouse-move [x y]
  (let [current-pt (mouse-location)
        offset-x (- x (:x @starting-pt))
        offset-y (- y (:y @starting-pt))]
  (.mouseMove
    robot
    (+ (:x current-pt) offset-x)
    (+ (:y current-pt) offset-y))))

(defn mouse-down [x y]
  (swap! starting-pt assoc :x x :y y))

(defn mouse-left-click []
  (.mousePress robot InputEvent/BUTTON1_MASK))

(defn mouse-right-click []
  (.mousePress robot InputEvent/BUTTON3_MASK))
