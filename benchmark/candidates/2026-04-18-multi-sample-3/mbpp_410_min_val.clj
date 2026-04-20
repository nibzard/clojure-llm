(defn min_val
  "	Write a function to find the minimum value in a given heterogeneous list."
  [listval]
  (when (seq listval)
    (reduce (fn [mn x]
              (if (neg? (compare x mn)) x mn))
            listval)))