(defn jacobsthal_num
  "Write a function to find the nth jacobsthal number. https://www.geeksforgeeks.org/jacobsthal-and-jacobsthal-lucas-numbers/ 0, 1, 1, 3, 5, 11, 21, 43, 85, 171, 341, 683, 1365, 2731, ..."
  [n]
  (cond
    (nil? n) nil
    (not (number? n)) nil
    (neg? n) nil
    (zero? n) 0
    (zero? (bit-shift-right n 1)) 1
    :else (loop [i 2
                 prev 1
                 prev-prev 1]
            (if (>= i n)
              prev
              (recur (inc i)
                     (+ (* 2 prev) (- prev-prev))
                     prev)))))