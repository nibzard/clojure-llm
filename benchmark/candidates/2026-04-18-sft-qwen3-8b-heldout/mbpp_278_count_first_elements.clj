(defn count_first_elements
  "	Write a function to find the number of elements that occurs before the list element in the given vector."
  [test_tup]
  (let [first-el (first test_tup)]
    (if (and (sequential? test_tup) (list? first-el))
      (dec (count test_tup))
      0)))