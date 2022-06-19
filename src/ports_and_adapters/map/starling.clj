(ns ports-and-adapters.map.starling
  (:require
   [ports-and-adapters.map.uses :as uses]))

(defn speak [_this] "trill")

(defn make []
  {::uses/speak speak})
