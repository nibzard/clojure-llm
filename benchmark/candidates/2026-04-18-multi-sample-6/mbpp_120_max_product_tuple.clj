(defn max_product_tuple
  "	Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (let [products (for [v list1
                       :when (and (vector? v) (= 2 (count v))
                                  (number? (first v)) (number? (second v)))]
                   (Math/abs (* (first v) (second v))))]
    (when (seq products)
      (apply max products))))