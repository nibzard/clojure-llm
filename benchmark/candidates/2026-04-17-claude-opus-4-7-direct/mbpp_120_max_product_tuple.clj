(defn max_product_tuple
  "Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (if (empty? list1)
    0
    (->> list1
         (map (fn [[a b]] (Math/abs (* a b))))
         (apply max))))