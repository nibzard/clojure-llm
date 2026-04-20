(defn substract_elements
  "Write a function that takes in two vectors and subtracts the elements of the first vector by the elements of the second vector with the same index."
  [test_tup1 test_tup2]
  (vec (map - (or test_tup1 []) (or test_tup2 []))))