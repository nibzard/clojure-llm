(defn sort_even
  "This function takes a list l and returns a list l' such that
  l' is identical to l in the odd indicies, while its values at the even indicies are equal
  to the values of the even indicies of l, but sorted.
  >>> (sort_even [1 2 3])
  [1 2 3]
  >>> (sort_even [5 6 3 4])
  [3 6 5 4]"
  [l]
  (let [even-values (sort (take-nth 2 l))]
    (vec
     (loop [xs l
            evens even-values
            idx 0
            acc []]
       (if (empty? xs)
         acc
         (if (even? idx)
           (recur (rest xs) (rest evens) (inc idx) (conj acc (first evens)))
           (recur (rest xs) evens (inc idx) (conj acc (first xs)))))))))