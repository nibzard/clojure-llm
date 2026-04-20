(defn sort_third
  "This function takes a list l and returns a list l' such that
  l' is identical to l in the indicies that are not divisible by three, while its values at the indicies that are divisible by three are equal
  to the values of the corresponding indicies of l, but sorted.
  >>> (sort_third [1 2 3])
  [1 2 3]
  >>> (sort_third [5 6 3 4 8 9 2])
  [2 6 3 4 8 9 5]"
  [l]
  (let [third-values (sort (take-nth 3 l))]
    (vec
     (second
      (reduce (fn [[sorted-vals acc] [idx v]]
                (if (zero? (mod idx 3))
                  [(rest sorted-vals) (conj acc (first sorted-vals))]
                  [sorted-vals (conj acc v)]))
              [third-values []]
              (map-indexed vector l))))))