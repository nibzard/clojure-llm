(defn and_tuples
  "Write a function to extract the elementwise and vectors from the given two vectors."
  [test_tup1 test_tup2]
  (mapv bit-and test_tup1 test_tup2))