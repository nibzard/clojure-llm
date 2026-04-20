(defn sum_squares
  [lst]
  (reduce-kv
    (fn [sum idx n]
      (+ sum
         (cond
           (and (zero? (mod idx 3)) (not (zero? (mod idx 4)))) (* n n)
           (zero? (mod idx 4)) (* n n n)
           :else n)))
    0
    (vec lst)))