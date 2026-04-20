(defn max_aggregate
  "Write a function to calculate the maximum aggregate from the list of vectors."
  [stdata]
  (when (seq stdata)
    (apply max (map #(apply + %) stdata))))