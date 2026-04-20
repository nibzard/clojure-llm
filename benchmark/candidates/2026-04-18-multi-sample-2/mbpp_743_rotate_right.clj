(defn rotate_right
  "Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (let [v (vec (or list []))
        n (count v)]
    (if (zero? n)
      '()
      (let [k (mod (long (or m 0)) n)
            split (- n k)]
        (seq (concat (subvec v split n) (subvec v 0 split)))))))