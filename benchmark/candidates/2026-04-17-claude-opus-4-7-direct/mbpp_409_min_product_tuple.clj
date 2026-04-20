(defn min_product_tuple
  "Write a function to find the minimum product from the pairs of vectors within a given list."
  [list1]
  (if (empty? list1)
    nil
    (->> list1
         (map (fn [pair] (apply * pair)))
         (apply min))))