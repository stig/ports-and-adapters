(ns ports-and-adapters.multi.demo
  (:require
   [orchestra.spec.test :as st]
   [ports-and-adapters.multi.donkey :as donkey]
   [ports-and-adapters.multi.horse :as horse]
   [ports-and-adapters.multi.uses :as uses]))

(st/instrument)

(def h (horse/make))

(def d (donkey/make))

(uses/speak h)

(uses/speak d)
