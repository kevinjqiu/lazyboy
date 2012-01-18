(ns lazyboy.main
  (:use [clj-webdriver.core
         :only [start
                find-it
                input-text
                click]]))

(def URL "https://signup.netflix.com/Login")

(defn -main [& args]
  (let [b (start {:browser :firefox} URL)]
    (-> b
      (find-it {:id "email"})
      (input-text "foo"))
    (-> b
      (find-it {:id "password"})
      (input-text "bar"))
    (-> b
      (find-it {:id "login-form-contBtn"})
      click)))
