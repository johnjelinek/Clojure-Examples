(ns myproject.AnimalProduction
  (:require [clojure.spec.alpha :as s])
  (:require [clojure.contrib.command-line :as ccl])
  (:gen-class))

;;animal farm spec
(s/def ::animal-type string?)
(s/def ::animal-neutered boolean?)
(s/def ::animal-age int?)
(s/def ::animal-index int?)
(s/def ::animal (s/keys :req [::animal-type ::animal-index]))
(s/def ::farm (s/* ::animal))

;;command line spec
(s/def ::arg any?)
(s/def ::args (s/+ ::arg))

(def animal-types ["pig" "goat" "cow" "horse"])

(defn generate-animal-population 
  ([population] "Recursion using recur"
   (loop [i 0 result-animal-set []]
     (if (< i population)
       (recur (inc i) (conj result-animal-set (get animal-types (rand-int (count animal-types)))))
       result-animal-set)))
  ([population se] "Recusion Using Method - This is unsafe and slower"
   (if (> population 0)
     (generate-animal-population (- population 1) (conj se (get animal-types (rand-int (count animal-types)))))
     se)
   ))

(defn gen-animals [population] (repeatedly population #(rand-nth animal-types)))

(defn create-farm [population]
  
  )

(s/fdef create-farm
  :args (:population int?)
  :ret ::farm)
  

(defn -main [& args]
  (println (gen-animals 10))
  
  (println (s/valid? ::animal {::animal-type "pig" ::animal-index false}))
  
  (let [parsed-args (s/conform ::args args)]
    (try
      (println (generate-animal-population (Integer/parseInt (get parsed-args 0)) []))
      (catch NumberFormatException e
        (do (println "Population value only accepted as Integer"))))))