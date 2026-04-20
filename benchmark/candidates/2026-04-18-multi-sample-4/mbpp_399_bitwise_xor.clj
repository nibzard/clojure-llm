(defn bitwise_xor
  "	Write a function to perform the mathematical bitwise xor operation across the given vectors."
  [test_tup1 test_tup2]
  (mapv bit-xor (or test_tup1 []) (or test_tup2 [])))