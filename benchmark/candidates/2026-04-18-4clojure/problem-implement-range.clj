(defn problem-implement-range [a b]
  (loop [i a
         acc '()]
    (if (>= i b)
      (reverse acc)
      (recur (inc i) (cons i acc)))))