(defn median_numbers
  "Write a function to find the median of three numbers."
  [a b c]
  (second (sort [a b c])))