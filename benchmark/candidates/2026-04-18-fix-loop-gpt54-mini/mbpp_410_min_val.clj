(defn min_val
  "	Write a function to find the minimum value in a given heterogeneous list."
  [listval]
  (reduce (fn [m x] (if (< (compare x m) 0) x m)) listval))