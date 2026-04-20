(defn max_occurrences
  "	Write a function to find the item with maximum frequency in a given list."
  [nums]
  (when (seq nums)
    (->> nums
         frequencies
         (reduce (fn [[best-item best-count] [item cnt]]
                   (if (> cnt best-count)
                     [item cnt]
                     [best-item best-count]))
                 [nil 0])
         first)))