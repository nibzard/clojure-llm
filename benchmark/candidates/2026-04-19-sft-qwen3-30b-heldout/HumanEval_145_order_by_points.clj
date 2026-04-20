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
  (sort-by (fn [x] [(reduce + (map #(Character/digit ^char % 10) (str (Math/abs (long x))))) x])
           nums))