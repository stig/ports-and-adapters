(ns ports-and-adapters.map.goose
  (:require
   [ports-and-adapters.map.uses :as uses]))

(defn speak [_this] "honk!")

(defn make []
  {::uses/speak speak})
