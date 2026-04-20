(defn geometric_sum [n]
  (if (< n 0)
    0.0
    (reduce + (map #(/ 1.0 (Math/pow 2 %)) (range (inc n))))))