(defn extract_even
  "Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (cond
    (vector? test_tuple) (into [] (keep extract_even) test_tuple)
    (coll? test_tuple) (keep extract_even test_tuple)
    (number? test_tuple) (when (even? test_tuple) test_tuple)
    :else test_tuple))