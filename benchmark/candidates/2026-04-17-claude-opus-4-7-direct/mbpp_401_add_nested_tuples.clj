(defn add_nested_tuples
  "Write a function to perform index wise addition of list elements in the given two nested lists."
  [test_tup1 test_tup2]
  (mapv (fn [inner1 inner2] (mapv + inner1 inner2)) test_tup1 test_tup2))