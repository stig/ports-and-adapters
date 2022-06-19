(ns ports-and-adapters.multi.horse
  (:require [ports-and-adapters.multi.uses :as uses]))

(defmethod uses/speak* ::horse [_this] "neeigh")

(defn make [] {::uses/type ::horse})
