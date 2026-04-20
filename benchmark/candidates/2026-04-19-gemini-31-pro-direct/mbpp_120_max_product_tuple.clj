(defn max_product_tuple
  "Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (apply max (map (fn [tuple]
                      (let [prod (apply * tuple)]
                        (if (neg? prod) (- prod) prod)))
                    list1))))