(defn min_product_tuple
  "	Write a function to find the minimum product from the pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (reduce min
            (keep (fn [pair]
                    (when (and (vector? pair)
                               (= 2 (count pair))
                               (every? number? pair))
                      (apply * pair)))
                  list1))))