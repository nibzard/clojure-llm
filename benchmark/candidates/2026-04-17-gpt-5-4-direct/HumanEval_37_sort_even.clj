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
     (loop [xs (seq l)
            evens even-sorted
            idx 0
            acc []]
       (if (nil? xs)
         acc
         (if (even? idx)
           (recur (next xs) (rest evens) (inc idx) (conj acc (first evens)))
           (recur (next xs) evens (inc idx) (conj acc (first xs)))))))))