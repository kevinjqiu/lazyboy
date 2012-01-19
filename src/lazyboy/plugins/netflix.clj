(ns lazyboy.plugins.netflix
  (:use [lazyboy.core :only [b]]
        [clj-webdriver.core :only [get-url find-it input-text click]]))

(def ^:dynamic *username* "")
(def ^:dynamic *password* "")

(def URL "https://signup.netflix.com/Login")

(defn login []
  (get-url b URL)
  (-> b
    (find-it {:id "email"})
    (input-text *username*))
  (-> b
    (find-it {:id "password"})
    (input-text *password*))
  (-> b
    (find-it {:id "login-form-contBtn"})
    click))
