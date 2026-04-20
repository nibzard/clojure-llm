(defn sum_digits
  "	Write a function to get the sum of the digits of a non-negative integer."
  [n]
  (reduce + (map #(Character/digit ^char % 10) (str n))))