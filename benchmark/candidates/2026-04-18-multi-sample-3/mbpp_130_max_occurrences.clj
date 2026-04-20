(defn max_occurrences
  "	Write a function to find the item with maximum frequency in a given list."
  [nums]
  (when (seq nums)
    (->> nums
         frequencies
         (reduce (fn [[best-item best-count] [item count]]
                   (if (> count best-count)
                     [item count]
                     [best-item best-count])))
         first)))