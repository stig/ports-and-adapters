(ns ports-and-adapters.todo.in-memory-storage
  (:require
   [ports-and-adapters.todo.entities :as entities]
   [ports-and-adapters.todo.storage-port :as port]))

(defn- add [this {::entities/keys [id] :as todo}]
  (swap! (::store this) #(-> %
                             (assoc-in [::todos id] todo)
                             (update-in [::order] conj id)))
  todo)

(defn- mark-done [this id]
  (let [store (::store this)]
    (if (contains? (::todos @store) id)
      (do (swap! store assoc-in [::todos id ::entities/done] true)
          true)
      false)))

(defn- list-all [this]
  (let [{::keys [todos order]} @(::store this)]
    (mapv todos order)))

(defn- delete [this id]
  (let [store (::store this)]
    (not= @store
          (swap! store #(-> %
                            (update-in [::todos] dissoc id)
                            (update-in [::order] (fn [xs] (remove #{id} xs))))))))

(defn make []
  {::store (atom {::todos {}
                  ::order []})
   ::port/add add
   ::port/mark-done mark-done
   ::port/list-all list-all
   ::port/delete delete})
