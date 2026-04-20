(defn max_aggregate
  "Write a function to calculate the maximum aggregate from the list of vectors."
  [stdata]
  (when (seq stdata)
    (->> stdata
         (reduce (fn [acc [k v]]
                   (update acc k (fnil + 0) v))
                 {})
         (apply max-key val)
         vec)))