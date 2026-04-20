(defn count_first_elements
  "Write a function to find the number of elements that occurs before the list element in the given vector."
  [test_tup]
  (if (seq test_tup)
    (dec (count test_tup))
    0))