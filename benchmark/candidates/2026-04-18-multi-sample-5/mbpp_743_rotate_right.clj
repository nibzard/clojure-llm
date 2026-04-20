(defn rotate_right
  "Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (let [xs (seq list)]
    (if (nil? xs)
      '()
      (let [v (vec xs)
            n (count v)
            k (mod (or m 0) n)
            split (- n k)]
        (seq (concat (subvec v split n) (subvec v 0 split)))))))