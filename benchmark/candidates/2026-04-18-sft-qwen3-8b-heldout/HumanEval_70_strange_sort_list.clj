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
  (let [xs (sort lst)]
    (loop [remaining xs
           acc '()]
      (if (empty? remaining)
        (seq acc)
        (let [first' (first remaining)
              rest' (rest remaining)
              last' (last remaining)]
          (recur (butlast rest')
                 (conj acc first' last')))))))