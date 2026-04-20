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
  (let [digit-sum (fn [n]
                    (let [abs-n (Math/abs n)]
                      (if (zero? abs-n)
                        0
                        (loop [n abs-n, s 0]
                          (if (zero? n)
                            s
                            (recur (quot n 10) (+ s (rem n 10))))))))]
    (mapv first
          (sort-by second
                   (map-indexed (fn [idx x] [x [(digit-sum x) idx]]) nums)))))