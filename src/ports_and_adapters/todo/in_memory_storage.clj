(ns ports-and-adapters.todo.in-memory-storage
  (:require
   [ports-and-adapters.todo.entities :as entities]
   [ports-and-adapters.todo.storage-port :as port]))

(defn- add [this {::entities/keys [id] :as todo}]
  (swap! (::store this) assoc id todo)
  todo)

(defn- mark-done [this id]
  (let [store (::store this)]
    (if (contains? @store id)
      (do (swap! store assoc-in [id ::entities/done] true)
          true)
      false)))

(defn- list-all [this]
  (vec (vals @(::store this))))

(defn- delete [this id]
  (let [store (::store this)]
    (if (contains? @store id)
      (do (swap! store dissoc id)
          true)
      false)))

(defn make []
  {::store (atom {})
   ::port/add add
   ::port/mark-done mark-done
   ::port/list-all list-all
   ::port/delete delete})
