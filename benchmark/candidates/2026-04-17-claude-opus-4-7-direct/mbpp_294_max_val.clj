(defn max_val
  "Write a function to find the maximum value in a given heterogeneous list."
  [listval]
  (if (empty? listval)
    nil
    (reduce max listval)))