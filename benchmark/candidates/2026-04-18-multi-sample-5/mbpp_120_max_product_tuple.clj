(defn max_product_tuple
  "	Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (reduce
      max
      (for [[a b] list1
            :when (and (number? a) (number? b))]
        (Math/abs (* a b))))))