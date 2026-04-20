(defn largest_prime_factor [n]
  (loop [n n
         d 2]
    (if (> (* d d) n)
      (if (> n 1) n d)
      (if (zero? (rem n d))
        (recur (quot n d) d)
        (recur n (inc d))))))