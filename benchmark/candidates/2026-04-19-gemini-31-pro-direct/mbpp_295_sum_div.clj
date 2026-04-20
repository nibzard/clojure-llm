(defn sum_div
  "Write a function to return the sum of all divisors of a number."
  [number]
  (if (< number 1)
    0
    (reduce + (filter #(zero? (rem number %)) (range 1 (inc number))))))