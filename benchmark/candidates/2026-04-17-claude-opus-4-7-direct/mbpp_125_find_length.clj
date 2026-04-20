(defn find_length
  "Write a function to find the maximum difference between the number of 0s and number of 1s in any sub-string of the given binary string."
  [string]
  (if (empty? string)
    0
    (let [vals (map #(if (= \0 %) 1 -1) string)
          max-suffix (reductions max (reverse vals))
          min-prefix (reductions min (cons 0 vals))]
      (apply max (map - max-suffix min-prefix)))))