(defn largest_divisor
  " For a given number n, find the largest number that divides n evenly, smaller than n
  >>> (largest_divisor 15)
  5"
  [n]
  (cond
    (nil? n) nil
    (<= n 1) nil
    :else
    (or
      (some #(when (zero? (mod n %)) %) (range (quot n 2) 0 -1))
      1)))