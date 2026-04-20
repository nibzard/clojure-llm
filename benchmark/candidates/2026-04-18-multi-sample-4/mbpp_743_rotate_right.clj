(defn rotate_right
  "	Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (let [coll (seq list)
        n    (count coll)]
    (cond
      (zero? n) '()
      :else
      (let [k (mod (long (or m 0)) n)]
        (if (zero? k)
          (apply list coll)
          (let [split-at-idx (- n k)]
            (apply list
                   (concat (drop split-at-idx coll)
                           (take split-at-idx coll)))))))))