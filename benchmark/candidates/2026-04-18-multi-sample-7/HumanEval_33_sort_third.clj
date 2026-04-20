(defn sort_third
  "This function takes a list l and returns a list l' such that
  l' is identical to l in the indicies that are not divisible by three, while its values at the indicies that are divisible by three are equal
  to the values of the corresponding indicies of l, but sorted.
  >>> (sort_third [1 2 3])
  [1 2 3]
  >>> (sort_third [5 6 3 4 8 9 2])
  [2 6 3 4 8 9 5]"
  [l]
  (let [third-idxs (range 0 (count l) 3)
        sorted-values (sort (map #(nth l %) third-idxs))]
    (reduce (fn [v [i x]]
              (assoc v i x))
            (vec l)
            (map vector third-idxs sorted-values))))