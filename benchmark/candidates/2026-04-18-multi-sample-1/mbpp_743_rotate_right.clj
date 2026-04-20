(defn rotate_right
  "	Write a function to rotate a given list by specified number of items to the right direction. https://www.geeksforgeeks.org/cljthon-program-right-rotate-list-n/"
  [list m]
  (let [xs (seq list)
        n  (count xs)]
    (cond
      (zero? n) '()
      (nil? m)  (apply list xs)
      :else
      (let [k (mod m n)
            split-idx (- n k)]
        (if (zero? k)
          (apply list xs)
          (let [[a b] (split-at split-idx xs)]
            (apply list (concat b a))))))))