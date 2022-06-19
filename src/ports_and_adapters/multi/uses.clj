(ns ports-and-adapters.multi.uses
  (:require [clojure.spec.alpha :as s]))

(defmulti speak* ::type)

(s/def ::type keyword?)

(s/def ::speaker (s/keys :req [::type]))

(defn speak [this]
  (speak* this))

(s/fdef speak
  :args (s/cat :this ::speaker)
  :ret string?)
