(defn largest_divisor
  " For a given number n, find the largest number that divides n evenly, smaller than n
  >>> (largest_divisor 15)
  5"
  [n]
  (first (filter #(zero? (rem n %)) (range (quot n 2) 0 -1))))