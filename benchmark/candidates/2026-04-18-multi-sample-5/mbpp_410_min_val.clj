(defn min_val
  "	Write a function to find the minimum value in a given heterogeneous list."
  [listval]
  (when-let [xs (seq listval)]
    (reduce (fn [m x]
              (if (neg? (compare x m)) x m))
            xs)))