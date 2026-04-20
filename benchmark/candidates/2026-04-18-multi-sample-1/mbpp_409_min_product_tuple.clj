(defn min_product_tuple
  "	Write a function to find the minimum product from the pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (reduce min
            (map (fn [v]
                   (when (and (vector? v) (>= (count v) 2))
                     (* (nth v 0) (nth v 1))))
                 (filter #(and (vector? %) (>= (count %) 2)) list1)))))