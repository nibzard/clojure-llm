(defn add_pairwise
  "	Write a function to find the pairwise addition of the neighboring elements of the given vector."
  [test_tup]
  (vec (map (partial apply +) (partition 2 1 test_tup))))