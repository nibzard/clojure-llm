(defn sort_even
  "This function takes a list l and returns a list l' such that
  l' is identical to l in the odd indicies, while its values at the even indicies are equal
  to the values of the even indicies of l, but sorted.
  >>> (sort_even [1 2 3])
  [1 2 3]
  >>> (sort_even [5 6 3 4])
  [3 6 5 4]"
  [l]
  (let [v (vec l)
        sorted-evens (sort (take-nth 2 v))]
    (vec
      (loop [i 0
             evens sorted-evens
             acc []]
        (if (= i (count v))
          acc
          (if (even? i)
            (recur (inc i) (rest evens) (conj acc (first evens)))
            (recur (inc i) evens (conj acc (v i)))))))))