(defn sumofFactors
  "Write a cljthon function to find the sum of even factors of a number."
  [n]
  (if (or (not (integer? n)) (<= n 0) (odd? n))
    0
    (reduce + (filter #(zero? (rem n %)) (range 2 (inc n) 2)))))