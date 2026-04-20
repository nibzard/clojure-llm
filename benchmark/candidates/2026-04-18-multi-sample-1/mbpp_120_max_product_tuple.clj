(defn max_product_tuple
  "	Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (reduce
     max
     (map (fn [v]
            (reduce
             max
             (map #(Math/abs (double %))
                  (for [x v
                        y v
                        :when (not= x y)]
                    (* x y)))))
          list1))))