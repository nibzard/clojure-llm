(defn max_fill
  [grid capacity]
  (if (seq grid)
    (quot (+ (reduce + (map #(reduce + %) grid)) (dec capacity)) capacity)
    0))