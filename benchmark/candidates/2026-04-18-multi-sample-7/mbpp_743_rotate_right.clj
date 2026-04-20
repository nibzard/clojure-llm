(defn rotate_right
  "Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (let [v (vec list)
        n (count v)]
    (cond
      (zero? n) '()
      (nil? m) (apply list v)
      :else (let [k (mod m n)
                  split-idx (- n k)]
              (apply list (concat (subvec v split-idx n)
                                  (subvec v 0 split-idx)))))))