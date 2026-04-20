(defn median_numbers
  "	Write a function to find the median of three numbers."
  [a b c]
  (let [nums [a b c]]
    (nth (sort nums) 1)))