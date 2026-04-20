(defn max_product_tuple
  "	Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (let [pair-products (fn [v]
                          (when (and (vector? v) (>= (count v) 2))
                            (map #(Math/abs (double (* %1 %2)))
                                 v
                                 (subvec v 1))))]
      (reduce max
              (keep identity
                    (mapcat #(or (pair-products %) [])
                            list1))))))