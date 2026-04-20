(defn sort_even
  "This function takes a list l and returns a list l' such that
  l' is identical to l in the odd indicies, while its values at the even indicies are equal
  to the values of the even indicies of l, but sorted.
  >>> (sort_even [1 2 3])
  [1 2 3]
  >>> (sort_even [5 6 3 4])
  [3 6 5 4]"
  [l]
  (let [even-vals (keep-indexed #(when (even? %1) %2) l)
        sorted-evens (sort even-vals)]
    (loop [s l
           result []
           sorted sorted-evens
           idx 0]
      (if (empty? s)
        result
        (let [current (first s)]
          (if (even? idx)
            (recur (rest s) (conj result (first sorted)) (rest sorted) (inc idx))
            (recur (rest s) (conj result current) sorted (inc idx))))))))