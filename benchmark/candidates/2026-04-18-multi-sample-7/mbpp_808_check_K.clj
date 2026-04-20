(defn check_K
  "Write a function to check if the given vectors contain the k or not."
  [test_tup K]
  (boolean (some #(= % K) test_tup)))