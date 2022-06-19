(ns ports-and-adapters.multi.donkey
  (:require [ports-and-adapters.multi.uses :as uses]))

(defmethod uses/speak* ::donkey [_this] "hee haw")

(defn make [] {::uses/type ::donkey})
