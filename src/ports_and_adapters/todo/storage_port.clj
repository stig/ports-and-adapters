(ns ports-and-adapters.todo.storage-port
  (:require
   [clojure.spec.alpha :as s]
   [ports-and-adapters.todo.entities :as entities]))

(s/def ::add fn?)
(s/def ::mark-done fn?)
(s/def ::list-all fn?)
(s/def ::delete fn?)

(s/def ::storage-port (s/keys :req [::add
                                    ::mark-done
                                    ::list-all
                                    ::delete]))

(defn add
  [this text]
  (let [todo (entities/->todo (random-uuid) text)]
    ((::add this) this todo)))

(s/fdef add
  :args (s/cat :this ::storage-port
               :todo ::entities/text)
  :ret ::entities/todo)

(defn mark-done
  [this id]
  ((::mark-done this) this id))

(s/fdef mark-done
  :args (s/cat :this ::storage-port
               :id ::entities/id)
  :ret boolean?)

(defn list-all
  [this]
  ((::list-all this) this))

(s/fdef list-all
  :args (s/cat :this ::storage-port)
  :ret (s/coll-of ::entities/todo))

(defn delete
  [this id]
  ((::delete this) this id))

(s/fdef delete
  :args (s/cat :this ::storage-port
               :id ::entities/id)
  :ret boolean?)
