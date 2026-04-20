(defn sort_third
  "This function takes a list l and returns a list l' such that
  l' is identical to l in the indicies that are not divisible by three, while its values at the indicies that are divisible by three are equal
  to the values of the corresponding indicies of l, but sorted.
  >>> (sort_third [1 2 3])
  [1 2 3]
  >>> (sort_third [5 6 3 4 8 9 2])
  [2 6 3 4 8 9 5]"
  [l]
  (let [v (vec l)
        sorted-third (sort (map v (range 0 (count v) 3)))]
    (reduce (fn [acc [idx val]]
              (assoc acc idx val))
            v
            (map vector (range 0 (count v) 3) sorted-third))))