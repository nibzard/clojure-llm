(defn sum_squares
  [lst]
  (reduce-kv
    (fn [sum idx n]
      (+ sum
         (cond
           (zero? (mod idx 3)) (* n n)
           (zero? (mod idx 4)) (* n n n)
           :else n)))
    0
    (vec lst)))