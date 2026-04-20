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
                    (->> (str (Math/abs (long n)))
                         (filter #(Character/isDigit ^char %))
                         (map #(Character/digit ^char % 10))
                         (reduce + 0)))]
    (->> nums
         (map-indexed (fn [idx n] [idx n (digit-sum n)]))
         (sort-by (fn [[idx n s]] [s idx]))
         (mapv second))))