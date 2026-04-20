(defn min_val
  "	Write a function to find the minimum value in a given heterogeneous list."
  [listval]
  (when (seq listval)
    (reduce (fn [minv x]
              (if (neg? (compare x minv)) x minv))
            listval)))