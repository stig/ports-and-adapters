(ns ports-and-adapters.todo.storage-port-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :refer [testing is]]
            [ports-and-adapters.todo.entities :as entities]
            [ports-and-adapters.todo.storage-port :as storage]))

(defn- add-test [handler]
  (handler
   (fn [adapter]
     (let [text "write test"]
       (testing "add returns a valid todo"
         (let [todo (storage/add adapter text)]
           (is (s/valid? ::entities/todo todo))

           (testing "... which is incomplete"
             (is (false? (::entities/done todo))))))

       (testing "we can add the same text again"
         (is (s/valid? ::entities/todo (storage/add adapter text)))))

     (testing "we can add different texts"
       (is (s/valid? ::entities/todo (storage/add adapter "something else")))))))

(defn- list-all-test [handler]
  (handler
   (fn [adapter]
     (testing "empty list for empty storage"
       (is (empty (storage/list-all adapter))))

     (testing "store with one item"
       (storage/add adapter "item 1")
       (let [lst (storage/list-all adapter)]
         (is (= 1 (count lst)))
         (is (s/valid? ::entities/todo (first lst)))
         (is (= "item 1" (-> lst first ::entities/text)))))

     (testing "store with more than one item"
       (dotimes [i 9]
         (storage/add adapter (str "item " i)))
       (is (= 10 (count (storage/list-all adapter))))))))

(defn- mark-done-test [handler]
  (handler
   (fn [adapter]
     (let [{::entities/keys [id done]} (storage/add adapter "Do it!")]

       (testing "initial task is false"
         (is (false? done)))

       (testing "marking unknown tasks complete returns false"
         (is (false? (storage/mark-done adapter (random-uuid)))))

       (testing "marking known task complete returns true"
         (is (true? (storage/mark-done adapter id)))

         (testing ".. and we can do it again"
           (is (true? (storage/mark-done adapter id))))

         (testing ".. and new state is reflected in the list"
           (is (true? (-> (storage/list-all adapter) first ::entities/done)))))))))

(defn- delete-test [handler]
  (handler
   (fn [adapter]
     (let [{::entities/keys [id]} (storage/add adapter "delete-me")]

       (testing "deleting an item not in the store is an error"
         (is (false? (storage/delete adapter (random-uuid)))))

       (testing "deleting an item in the store"
         (is (true? (storage/delete adapter id)))
         (testing ".. is not repeatable"
           (is (false? (storage/delete adapter id))))

         (testing "... and store is now empty"
           (is (empty (storage/list-all adapter)))))))))

(defn suite [handler]
  (add-test handler)
  (list-all-test handler)
  (mark-done-test handler)
  (delete-test handler)
  nil)
