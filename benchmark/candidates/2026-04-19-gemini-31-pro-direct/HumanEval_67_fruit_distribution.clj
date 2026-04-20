(defn fruit_distribution [s n]
  (- n (reduce + (map #(Integer/parseInt %) (re-seq #"\d+" s)))))