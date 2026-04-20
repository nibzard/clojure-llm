(defn max_occurrences
  "Write a function to find the item with maximum frequency in a given list."
  [nums]
  (when (seq nums)
    (->> nums
         frequencies
         (sort-by (comp - val))
         ffirst)))