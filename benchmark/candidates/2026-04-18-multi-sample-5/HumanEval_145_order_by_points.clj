(defn order_by_points
  "Write a function which sorts the given list of integers
  in ascending order according to the sum of their digits.
  Note: if there are several items with similar sum of their digits,
  order them based on their index in original list.
  For example:
  >>> (order_by_points [1 11 -1 -11 -12])
  [-1 -11 1 -12 11]
  >>> (order_by_points [])
  []"
  [nums]
  (letfn [(digit-sum [n]
            (->> (str n)
                 (remove #{\-})
                 (map #(- (int %) (int \0)))
                 (reduce + 0)))]
    (->> nums
         (map-indexed vector)
         (sort-by (fn [[idx n]] [(digit-sum n) idx]))
         (mapv second))))