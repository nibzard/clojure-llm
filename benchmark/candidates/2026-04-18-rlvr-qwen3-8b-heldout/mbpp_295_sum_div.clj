(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (long (or number 0))]
    (if (pos? n)
      (reduce + (filter #(zero? (mod n %)) (range 1 (inc n))))
      0)))