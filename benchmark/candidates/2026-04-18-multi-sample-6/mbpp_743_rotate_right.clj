(defn rotate_right
  "	Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (let [coll (seq list)
        n (count coll)]
    (cond
      (zero? n) '()
      (nil? m) (apply list coll)
      :else (let [k (mod m n)
                  split-idx (- n k)]
              (apply list
                     (concat (drop split-idx coll)
                             (take split-idx coll))))))