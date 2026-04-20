(defn sort_numeric_strings
  "Write a function to sort a given list of strings of numbers numerically."
  [nums_str]
  (when nums_str
    (sort-by bigdec nums_str)))