(ns ports-and-adapters.protocol.uses
  (:require [clojure.spec.alpha :as s]))

(defprotocol Speak
  (speak* [this]))

(s/def ::speaker record?)

(defn speak [this]
  (speak* this))

(s/fdef speak
  :args (s/cat :this ::speaker)
  :ret string?)
