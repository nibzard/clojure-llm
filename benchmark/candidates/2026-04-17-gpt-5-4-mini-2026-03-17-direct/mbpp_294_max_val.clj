(defn max_val
  "	Write a function to find the maximum value in a given heterogeneous list."
  [listval]
  (when (seq listval)
    (reduce
      (fn [m x]
        (if (neg? (compare m x)) x m))
      (first listval)
      (rest listval))))