(defn sort_numeric_strings
  "Write a function to sort a given list of strings of numbers numerically."
  [nums_str]
  (sort #(compare (BigInteger. %1) (BigInteger. %2)) nums_str))