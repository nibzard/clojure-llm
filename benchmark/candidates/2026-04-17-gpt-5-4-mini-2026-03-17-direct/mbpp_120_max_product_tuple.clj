(defn max_product_tuple
  "	Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (let [pairs (for [v1 list1
                    v2 list1
                    :when (not= v1 v2)
                    x v1
                    y v2]
                (Math/abs (double (* x y))))]
    (if (seq pairs)
      (apply max pairs)
      0.0)))