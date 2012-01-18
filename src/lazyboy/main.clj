(ns lazyboy.main
  (:use clj-webdriver.core))

(defn -main [& args]
  (def b (start {:browser :firefox} "https://github.com"))
  (-> b
    (find-it {:text "Login"})
    click)
  (-> b
    (find-it {:class "text" :name "login"})
    (input-text "foo"))
  (-> b
    (find-it {:xpath "//input[@id='password']"})
    (input-text "bar"))
  (-> b
    (find-it {:tag :input :value #"Log"})
    click))
