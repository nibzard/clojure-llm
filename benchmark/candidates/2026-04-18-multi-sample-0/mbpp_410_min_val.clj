(defn min_val
  "	Write a function to find the minimum value in a given heterogeneous list."
  [listval]
  (when-let [vals (seq (filter number? listval))]
    (reduce min vals)))