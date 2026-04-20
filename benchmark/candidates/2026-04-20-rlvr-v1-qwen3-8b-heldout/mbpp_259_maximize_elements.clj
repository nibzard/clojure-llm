(defn maximize_elements
  "	Write a function to maximize the given two lists."
  [test_tup1 test_tup2]
  (mapv max (or test_tup1 []) (or test_tup2 [])))