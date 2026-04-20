(defn sort_third
  "This function takes a list l and returns a list l' such that
  l' is identical to l in the indicies that are not divisible by three, while its values at the indicies that are divisible by three are equal
  to the values of the corresponding indicies of l, but sorted.
  >>> (sort_third [1 2 3])
  [1 2 3]
  >>> (sort_third [5 6 3 4 8 9 2])
  [2 6 3 4 8 9 5]"
  [l]
  (let [third-values (sort (map first (filter #(zero? (mod (second %) 3))
                                              (map vector l (range)))))]
    (loop [xs l
           i 0
           sv third-values
           acc []]
      (if (empty? xs)
        acc
        (if (zero? (mod i 3))
          (recur (rest xs) (inc i) (rest sv) (conj acc (first sv)))
          (recur (rest xs) (inc i) sv (conj acc (first xs))))))))