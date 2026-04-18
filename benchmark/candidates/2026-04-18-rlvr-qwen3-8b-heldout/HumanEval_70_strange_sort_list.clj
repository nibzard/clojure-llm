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
    (if (zero? n)
      []
      (loop [vs v
             i 0
             acc []]
        (if (= i n)
          (vec acc)
          (let [a (if (even? i) (reduce max vs) (reduce min vs))
                vs' (disj vs a)]
            (recur vs' (inc i) (conj acc a))))))))