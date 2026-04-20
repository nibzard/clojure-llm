(defn largest_divisor
  "For a given number n, find the largest number that divides n evenly, smaller than n"
  [n]
  (if (<= n 1)
    nil
    (or (first (filter #(zero? (rem n %)) (range (dec n) 0 -1)))
        1)))