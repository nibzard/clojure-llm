(defn add_pairwise
  "	Write a function to find the pairwise addition of the neighboring elements of the given vector."
  [test_tup]
  (->> test_tup
       (partition 2 1)
       (mapv (partial apply +))))