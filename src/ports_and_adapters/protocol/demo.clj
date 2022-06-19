(ns ports-and-adapters.protocol.demo
  (:require
   [orchestra.spec.test :as st]
   [ports-and-adapters.protocol.cat :as cat]
   [ports-and-adapters.protocol.mouse :as mouse]
   [ports-and-adapters.protocol.uses :as uses]))

(st/instrument)

(def c (cat/make))

(def m (mouse/make))

(uses/speak c)

(uses/speak m)
