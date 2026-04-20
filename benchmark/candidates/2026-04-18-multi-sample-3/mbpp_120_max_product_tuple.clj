(defn max_product_tuple
  "	Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (reduce
      max
      (map (fn [v]
             (reduce
               max
               (map (fn [x] (Math/abs (double (reduce * 1 v x))))
                    v)))
           list1))))