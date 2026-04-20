(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (long (Math/abs (long number)))]
    (if (zero? n)
      0
      (reduce + (filter #(zero? (mod n %)) (range 1 (inc n)))))))