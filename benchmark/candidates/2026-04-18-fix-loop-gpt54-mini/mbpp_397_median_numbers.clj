(defn median_numbers
  "	Write a function to find the median of three numbers."
  [a b c]
  (nth (sort [a b c]) 1))