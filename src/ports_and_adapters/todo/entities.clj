(ns ports-and-adapters.todo.entities
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]))

(s/def ::id uuid?)
(s/def ::done boolean?)
(s/def ::text (s/and string? #(< 0 (count %))))

(s/def ::todo (s/keys :req [::id
                            ::done
                            ::text]))

(defn ->todo [id text]
  {::id id
   ::done false
   ::text text})

(s/fdef ->todo
  :args (s/cat :id ::id
               :text ::text)
  :ret ::todo)
