(defn sum-divisors
  "Helper function to calculate the sum of divisors of a given integer."
  [n]
  (if (or (nil? n) (zero? n))
    0
    (reduce + (filter #(zero? (mod n %)) (range 1 (inc n))))))