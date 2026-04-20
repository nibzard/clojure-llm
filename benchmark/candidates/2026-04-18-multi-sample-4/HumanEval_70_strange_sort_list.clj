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
  (let [v (vec (sort lst))
        n (count v)]
    (loop [l 0
           r (dec n)
           take-min? true
           acc []]
      (if (> l r)
        acc
        (if take-min?
          (recur (inc l) r false (conj acc (v l)))
          (recur l (dec r) true (conj acc (v r))))))))