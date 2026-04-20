(defn sum_digits
  "Write a function to get the sum of the digits of a non-negative integer."
  [n]
  (if (nil? n)
    0
    (reduce + (map #(Character/digit % 10) (str n)))))