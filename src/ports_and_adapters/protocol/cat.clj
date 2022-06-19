(ns ports-and-adapters.protocol.cat
  (:require [ports-and-adapters.protocol.uses :as uses]))

(defrecord CatSpeak []
  uses/Speak
  (speak* [_this] "meow"))

(defn make []
  (map->CatSpeak {}))
