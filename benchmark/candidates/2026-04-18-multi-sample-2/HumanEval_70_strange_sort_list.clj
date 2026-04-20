(defn strange_sort_list
  "Given list of integers, return list in strange order.
  Strange sorting, is when you start with the minimum value,
  then maximum of the remaining integers, then minimum and so on.
  Examples:
  >>> (strange_sort_list [1 2 3 4])
  [1 4 2 3]
  >>> (strange_sort_list [5 5 5 5])
  [5 5 5 5]
  >>> (strange_sort_list [])
  []"
  [lst]
  (let [xs (vec (sort lst))
        n  (count xs)]
    (loop [i 0
           j (dec n)
           take-min? true
           acc []]
      (if (> i j)
        acc
        (if take-min?
          (recur (inc i) j false (conj acc (xs i)))
          (recur i (dec j) true (conj acc (xs j))))))))