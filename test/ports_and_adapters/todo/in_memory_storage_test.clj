(ns ports-and-adapters.todo.in-memory-storage-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [ports-and-adapters.todo.in-memory-storage :as in-memory-storage]
   [ports-and-adapters.todo.storage-port :as storage]
   [clojure.spec.alpha :as s]
   [ports-and-adapters.todo.entities :as entities]))

(deftest make
  (testing "storage is initially empty"
    (let [s (in-memory-storage/make)]
      (is (empty @(::in-memory-storage/store s))))))

(deftest add
  (let [s (in-memory-storage/make)]
    (testing "add returns a valid todo"
      (let [todo (storage/add s "Write tests")]
        (is (s/valid? ::entities/todo todo))

        (testing "... which is incomplete"
          (is (false? (::entities/done todo)))))

      (testing "... and store is no longer empty"
        (is (not-empty @(::in-memory-storage/store s)))))))

(deftest list-all
  (let [s (in-memory-storage/make)]
    (testing "empty list for empty storage"
      (is (empty (storage/list-all s))))

    (testing "store with one item"
      (storage/add s "item 1")
      (let [lst (storage/list-all s)]
        (is (= 1 (count lst)))
        (is (s/valid? ::entities/todo (first lst)))
        (is (= "item 1" (-> lst first ::entities/text)))))

    (testing "store with more than one item"
      (dotimes [i 9]
        (storage/add s (str "item " i)))
      (is (= 10 (count (storage/list-all s)))))))

(deftest mark-done
  (let [s (in-memory-storage/make)
        {::entities/keys [id done]} (storage/add s "Doit")]

    (testing "initial task is false"
      (is (false? done)))

    (testing "marking unknown tasks complete returns false"
      (is (false? (storage/mark-done s (random-uuid)))))

    (testing "marking known task complete returns true"
      (is (true? (storage/mark-done s id)))

      (testing ".. and we can do it again"
        (is (true? (storage/mark-done s id))))

      (testing ".. and new state is reflected in the list"
        (is (true? (-> (storage/list-all s) first ::entities/done)))))))

(deftest delete
  (let [s (in-memory-storage/make)
        {::entities/keys [id]} (storage/add s "delete-me")]

    (testing "deleting an item not in the store is an error"
      (is (false? (storage/delete s (random-uuid)))))

    (testing "deleting an item in the store"
      (is (true? (storage/delete s id)))
      (testing ".. is not repeatable"
        (is (false? (storage/delete s id))))

      (testing "... and store is now empty"
        (is (empty (storage/list-all s)))))))
