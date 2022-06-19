(ns ports-and-adapters.protocol.mouse
  (:require [ports-and-adapters.protocol.uses :as uses]))

(defrecord MouseSpeak []
  uses/Speak
  (speak* [_this] "squeak!"))

(defn make []
  (map->MouseSpeak {}))
