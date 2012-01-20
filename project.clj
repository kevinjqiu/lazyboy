(defproject lazyboy "0.0.1-SNAPSHOT"
  :description "A remote control for my TV-connecting laptop so I don't have to leave my couch."
  :dependencies [[clojure "1.3.0"]
                 [clj-webdriver "0.5.0-alpha4"]
                 [org.clojure/tools.cli "0.2.1"]
                 [org.clojure/data.json "0.1.1"]
                 [noir "1.2.2"]]
  :main lazyboy.main)
