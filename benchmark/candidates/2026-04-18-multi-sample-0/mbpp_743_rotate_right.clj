(defn rotate_right
  "	Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (let [xs (seq list)]
    (if (or (nil? xs) (zero? m))
      (if (nil? xs) '() list)
      (let [v (vec xs)
            n (count v)
            k (mod m n)]
        (if (zero? k)
          (apply list v)
          (let [split (- n k)]
            (apply list (concat (subvec v split) (subvec v 0 split)))))))))