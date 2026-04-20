(defn largest_divisor
  " For a given number n, find the largest number that divides n evenly, smaller than n
  >>> (largest_divisor 15)
  5"
  [n]
  (cond
    (nil? n) nil
    (<= n 1) nil
    :else
    (let [limit (long (Math/sqrt n))]
      (or
       (some (fn [d]
               (when (zero? (mod n d))
                 (quot n d)))
             (range 2 (inc limit)))
       1))))