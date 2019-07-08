(ns myproject.core
  (:require [clojure.spec.alpha :as s])
  (:gen-class))

(s/def ::animal-name string?)
(s/def ::neutered boolean?)
(s/def ::age int?)

(def animal-types ["pig" "goat" "cow" "horse"])
(def set-of-animals [])

(defn generate-animal-population [population-count animal-set]
  (generate-animal-population((- 1 population-count) (conj animal-set (get animal-types (rand-int (count animal-types))))))
  animal-set)

(defn -main
  "Executed at the start of this program"
  [& args]
  (println (generate-animal-population 5 {})
  
  ))