(ns lazyboy.robot
  (:use    [clojure.tools.logging :only (info)])
  (:import [java.awt Robot MouseInfo Toolkit]
           [java.awt.event InputEvent]))

(def robot (Robot.))

(def starting-pt (atom {:x 0 :y 0}))

(defn screen-dimension []
  (-> (Toolkit/getDefaultToolkit) .getScreenSize))

(defn mouse-location []
  (let [pt (.getLocation (MouseInfo/getPointerInfo))]
    {:x (.getX pt)
     :y (.getY pt)}))

(defn mouse-move [x y]
  (info (str "mouse-move, x=" x ",y=" y))
  (let [current-pt (mouse-location)
        offset-x (- x (:x @starting-pt))
        offset-y (- y (:y @starting-pt))]
  (.mouseMove
    robot
    (+ (:x current-pt) offset-x)
    (+ (:y current-pt) offset-y))))

(defn mouse-down [x y]
  (info (str "mouse-down, x=" x ",y=" y))
  (swap! starting-pt assoc :x x :y y))

(defn mouse-left-click []
  (info "mouse-left-click")
  (doto robot
    (.mousePress InputEvent/BUTTON1_MASK)
    (.mouseRelease InputEvent/BUTTON1_MASK)))

(defn mouse-right-click []
  (info "mouse-right-click")
  (doto robot
    (.mousePress InputEvent/BUTTON3_MASK)
    (.mouseRelease InputEvent/BUTTON3_MASK)))
