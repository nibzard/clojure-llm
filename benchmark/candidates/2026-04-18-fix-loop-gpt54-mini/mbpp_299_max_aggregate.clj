(defn max_aggregate
  "	Write a function to calculate the maximum aggregate from the list of vectors."
  [stdata]
  (apply max (map #(apply + %) stdata)))