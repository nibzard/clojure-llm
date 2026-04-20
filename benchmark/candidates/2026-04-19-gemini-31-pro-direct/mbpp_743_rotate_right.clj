(defn rotate_right
  "	Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (if (empty? list)
    list
    (let [n (count list)
          k (mod m n)
          split-idx (- n k)]
      (concat (drop split-idx list) (take split-idx list)))))