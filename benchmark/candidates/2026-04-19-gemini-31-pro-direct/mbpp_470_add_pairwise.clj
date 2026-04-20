(defn add_pairwise
  "Write a function to find the pairwise addition of the neighboring elements of the given vector."
  [test_tup]
  (map + test_tup (rest test_tup)))