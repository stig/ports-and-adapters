(ns ports-and-adapters.map.uses
  (:require [clojure.spec.alpha :as s]
            [clojure.test :as test]))

(s/def ::speak test/function?)

(s/def ::speaker (s/keys :req [::speak]) )

(defn speak [this]
  ((::speak this) this))

(s/fdef speak
  :args (s/cat :this ::speaker)
  :ret string?)
