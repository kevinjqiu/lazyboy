(ns lazyboy.netflix
  (:use [clj-webdriver.core :only [attribute get-url find-it find-them input-text click]])
  (:import [org.openqa.selenium By]))

(def URL "https://signup.netflix.com/Login")

(defrecord Movie [id name url])
(defrecord Genre [name url])

;(def *genre-map* (ref {}))

(defn login [b username password]
  (get-url b URL)
  (-> b (find-it {:id "email"}) (input-text username))
  (-> b (find-it {:id "password"}) (input-text password))
  (-> b (find-it {:id "login-form-contBtn"}) click))

(defn- create-movie [elem]
  (let [webelem (:webelement elem)
        img-elem (.findElement webelem (By/tagName "img"))
        a-elem (.findElement webelem (By/tagName "a"))]
    (Movie.
      (attribute elem "id")
      (.getAttribute img-elem "alt")
      (.getAttribute a-elem "href"))))

(defn movies [b]
  (map create-movie (find-them b {:css "span.boxShot"})))

(defn- create-genre [elem]
  (let [webelem (:webelement elem)
        a-elem (.findElement webelem (By/tagName "a"))
        get-title (fn [title] (second (re-matches #"Go to (.*)" title)))]
    (Genre. 
      (get-title (.getAttribute a-elem "title"))
      (.getAttribute a-elem "href"))))

(defn genres [b]
  (map create-genre (find-them b {:css "#nav-edgenre-dd .nav-item"})))
