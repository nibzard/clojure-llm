(defn sort_numeric_strings
  "Write a function to sort a given list of strings of numbers numerically."
  [nums_str]
  (sort-by #(Long/parseLong %) nums_str))