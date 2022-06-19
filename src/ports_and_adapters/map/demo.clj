(ns ports-and-adapters.map.demo
  (:require
   [orchestra.spec.test :as st]
   [ports-and-adapters.map.goose :as goose]
   [ports-and-adapters.map.starling :as starling]
   [ports-and-adapters.map.uses :as uses]))

(st/instrument)

(def g (goose/make))

(def s (starling/make))

(uses/speak g)

(uses/speak s)
