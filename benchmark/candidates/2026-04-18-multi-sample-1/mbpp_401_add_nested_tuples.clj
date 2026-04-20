(defn add_nested_tuples
  "	Write a function to perform index wise addition of list elements in the given two nested lists."
  [test_tup1 test_tup2]
  (cond
    (and (sequential? test_tup1) (sequential? test_tup2))
    (mapv add_nested_tuples test_tup1 test_tup2)

    (and (number? test_tup1) (number? test_tup2))
    (+ test_tup1 test_tup2)

    :else
    nil))