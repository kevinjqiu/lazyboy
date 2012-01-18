(ns lazyboy.core
  (:use [clj-webdriver.core :only [new-driver]]))

(def b (new-driver {:browser :firefox}))
