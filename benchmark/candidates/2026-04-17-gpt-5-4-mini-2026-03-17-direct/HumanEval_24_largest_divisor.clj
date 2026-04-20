(defn largest_divisor
  "For a given number n, find the largest number that divides n evenly, smaller than n."
  [n]
  (when (and (number? n) (> n 1))
    (let [limit (long (Math/floor (Math/sqrt n)))]
      (loop [i limit]
        (cond
          (zero? i) 1
          (zero? (mod n i)) (quot n i)
          :else (recur (dec i)))))))