(defn index_multiplication
  "Write a function to perform index wise multiplication of list elements in the given two lists."
  [test_tup1 test_tup2]
  (mapv * (or test_tup1 []) (or test_tup2 [])))