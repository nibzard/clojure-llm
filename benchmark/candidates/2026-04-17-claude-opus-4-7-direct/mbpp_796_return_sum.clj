(defn return_sum
  "Write function to find the sum of all items in the given map."
  [dict]
  (if (map? dict)
    (reduce + (vals dict))
    0))