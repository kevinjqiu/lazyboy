(ns lazyboy.request
  (:use [clojure.data.json :only (read-json)]))

(defrecord Request [command args])

(defn request-factory [raw-input]
  (let [req-obj (read-json raw-input)]
    (Request. (:command req-obj) (:args req-obj))))

(defprotocol IDispatch
  (dispatch [request]))

(extend-type Request
  IDispatch
  (dispatch [request]
    (println (str "dispatching " request))))
