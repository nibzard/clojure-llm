(defn maximize_elements
  "	Write a function to maximize the given two lists."
  [test_tup1 test_tup2]
  (mapv #(max %1 %2) test_tup1 test_tup2))