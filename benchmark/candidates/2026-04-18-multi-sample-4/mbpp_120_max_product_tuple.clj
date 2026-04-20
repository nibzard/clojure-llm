(defn max_product_tuple
  "	Write a function to find the maximum absolute product between numbers in pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (reduce
     max
     (map (fn [v]
            (let [nums (filter number? v)]
              (when (>= (count nums) 2)
                (->> (for [a nums
                           b nums
                           :when (not= a b)]
                       (Math/abs (* a b)))
                     (reduce max)))))
          list1))))