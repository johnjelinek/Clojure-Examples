(ns myproject.core
  (:require [clojure.spec.alpha :as s])
  (:gen-class))

(s/def ::animal-name string?)
(s/def ::neutered boolean?)
(s/def ::age int?)

(def animal-types ["pig" "goat" "cow" "horse"])

(defn generate-animal-population [n]
  "Generates the animal population based on n amount of animals"
  (def set-of-animals [])
  (doseq [x (range 0 n)]
    (let [set-of-animals (conj set-of-animals (get animal-types (rand-int (count animal-types))))]))
  set-of-animals)

(defn -main
  "Executed at the start of this program"
  [& args]
  (println (generate-animal-population 10))
  
  )
