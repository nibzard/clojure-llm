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
  (loop [src (sort lst)
         dst []]
    (if (empty? src)
      dst
      (let [min-val (first src)
            max-val (last src)
            remaining (if (= 1 (count src))
                        []
                        (next (butlast src)))]
        (recur remaining (conj dst min-val max-val))))))