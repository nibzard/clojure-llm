(defn max_product_tuple
  "	Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (let [pair-products (for [v list1
                            :when (and (vector? v) (>= (count v) 2))
                            [a b] [(take 2 v)]
                            :when (and (number? a) (number? b))]
                        (Math/abs (* a b)))]
    (when (seq pair-products)
      (apply max pair-products))))