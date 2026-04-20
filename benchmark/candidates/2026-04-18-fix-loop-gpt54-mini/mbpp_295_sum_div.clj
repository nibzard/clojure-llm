(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (reduce + (filter #(zero? (mod number %)) (range 1 (inc number)))))