(ns lazyboy.request
  (:use [clojure.data.json :only (read-json)]))

(defn request [raw-input]
  (let [req-obj (read-json raw-input)]
    (Request. (:command req-obj) (:args req-obj))))

(defrecord Request [command args])

(defprotocol IDispatch
  (dispatch [request]))

(extend-type Request
  IDispatch
  (dispatch [request]
    (println (str "dispatching " request))))
