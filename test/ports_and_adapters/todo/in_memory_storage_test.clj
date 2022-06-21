(ns ports-and-adapters.todo.in-memory-storage-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [ports-and-adapters.todo.in-memory-storage :as in-memory-storage]
   [ports-and-adapters.todo.storage-port-test :as storage-port-test]))

(deftest make
  (testing "storage is initially empty"
    (let [s (in-memory-storage/make)]
      (is (empty @(::in-memory-storage/store s))))))

(deftest in-memory-storage-conforms-to-specification
  (letfn [(handler [f]
            (f (in-memory-storage/make)))]
    (storage-port-test/suite handler)))
