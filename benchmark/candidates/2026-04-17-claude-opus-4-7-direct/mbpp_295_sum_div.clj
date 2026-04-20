(defn sum_div
  "Write a function to return the sum of all divisors of a number."
  [number]
  (if (or (nil? number) (<= number 0))
    0
    (reduce + (filter #(zero? (mod number %)) (range 1 (inc number))))))