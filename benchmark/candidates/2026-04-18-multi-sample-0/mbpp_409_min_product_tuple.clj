(defn min_product_tuple
  "Write a function to find the minimum product from the pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (reduce min
            (map (fn [pair]
                   (reduce * 1 pair))
                 list1))))