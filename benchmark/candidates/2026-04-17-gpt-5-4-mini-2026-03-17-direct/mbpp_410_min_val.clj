(defn min_val
  "Write a function to find the minimum value in a given heterogeneous list."
  [listval]
  (when (seq listval)
    (reduce (fn [m x] (if (< x m) x m)) listval)))