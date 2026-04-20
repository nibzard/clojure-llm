(defn add_pairwise
  "	Write a function to find the pairwise addition of the neighboring elements of the given vector."
  [test_tup]
  (if (seq test_tup)
    (mapv + test_tup (rest test_tup))
    []))