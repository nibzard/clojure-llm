(defn rotate_right
  "Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (let [v (vec list)
        n (count v)]
    (if (zero? n)
      list
      (let [k (mod m n)]
        (concat (subvec v (- n k)) (subvec v 0 (- n k)))))))