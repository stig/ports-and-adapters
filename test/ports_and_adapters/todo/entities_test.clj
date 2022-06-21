(ns ports-and-adapters.todo.entities-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.spec.test.alpha :as st]))

(deftest entities
  (is (= {:total 1
          :check-passed 1}
         (-> 'ports-and-adapters.todo.entities
             st/enumerate-namespace
             st/check
             st/summarize-results))))
