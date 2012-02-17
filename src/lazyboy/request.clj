(ns lazyboy.request
  (:use [clojure.data.json :only (read-json)]
        [lazyboy.robot :only (mouse-move
                               mouse-down
                               mouse-left-click
                               mouse-right-click)]))

(defrecord Request [command args])

(defn request-factory [raw-input]
  (let [req-obj (read-json raw-input)]
    (Request. (:command req-obj) (:args req-obj))))

(defprotocol IDispatch
  (dispatch [request]))

(defonce command-map (atom {}))

(defn get-command [command-str]
  ((keyword command-str) @command-map))

(defn add-command [command-keyword handler]
  (swap! command-map assoc command-keyword handler))

(add-command
  :mouse-move
  (fn [args]
    (let [x (Integer. (:x args))
          y (Integer. (:y args))]
      (do
        (mouse-move x y)
        {:response "done"}))))

(add-command
  :mouse-down
  (fn [args]
    (let [x (Integer. (:x args))
          y (Integer. (:y args))]
      (do
        (mouse-down x y)
        {:response "done"}))))

(add-command
  :mouse-left-click
  (fn [args]
    (mouse-left-click)
    {:response "done"}))

(add-command
  :mouse-right-click
  (fn [args]
    (mouse-right-click)
    {:response "done"}))

(extend-type Request
  IDispatch
  (dispatch [request]
    ((get-command (:command request)) (:args request))))
