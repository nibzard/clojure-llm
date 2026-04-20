(defn bitwise_xor
  "	Write a function to perform the mathematical bitwise xor operation across the given vectors."
  [test_tup1 test_tup2]
  (let [v1 (or test_tup1 [])
        v2 (or test_tup2 [])]
    (vec (map bit-xor v1 v2))))