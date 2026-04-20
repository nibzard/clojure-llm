(defn sort_even
  "This function takes a list l and returns a list l' such that
  l' is identical to l in the odd indicies, while its values at the even indicies are equal
  to the values of the even indicies of l, but sorted.
  >>> (sort_even [1 2 3])
  [1 2 3]
  >>> (sort_even [5 6 3 4])
  [3 6 5 4]"
  [l]
  (let [even-sorted (sort (take-nth 2 l))]
    (vec
     (first
      (reduce (fn [[acc evens] [idx x]]
                (if (even? idx)
                  [(conj acc (first evens)) (rest evens)]
                  [(conj acc x) evens]))
              [[] even-sorted]
              (map-indexed vector l))))))