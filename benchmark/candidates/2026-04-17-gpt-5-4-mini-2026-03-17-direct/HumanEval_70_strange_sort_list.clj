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
  (let [sorted (sort lst)]
    (loop [lo 0
           hi (dec (count sorted))
           take-min? true
           acc []]
      (if (> lo hi)
        acc
        (if take-min?
          (recur (inc lo) hi false (conj acc (nth sorted lo)))
          (recur lo (dec hi) true (conj acc (nth sorted hi))))))))