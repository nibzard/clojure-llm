(defn bitwise_xor
  "	Write a function to perform the mathematical bitwise xor operation across the given vectors."
  [test_tup1 test_tup2]
  (vec (map bit-xor test_tup1 test_tup2)))