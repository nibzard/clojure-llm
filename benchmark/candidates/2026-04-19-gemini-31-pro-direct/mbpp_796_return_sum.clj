(defn return_sum
  "Write function to find the sum of all items in the given map."
  [dict]
  (reduce + 0 (vals dict)))