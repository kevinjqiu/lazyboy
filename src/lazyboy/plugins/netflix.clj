(ns lazyboy.plugins.netflix
  (:use [lazyboy.core :only [b]]
        [clj-webdriver.core :only [get-url find-it input-text click]]))

(def URL "https://signup.netflix.com/Login")

(defn login []
  (get-url b URL)
  (-> b
    (find-it {:id "email"})
    (input-text "foo"))
  (-> b
    (find-it {:id "password"})
    (input-text "bar"))
  (-> b
    (find-it {:id "login-form-contBtn"})
    click))
