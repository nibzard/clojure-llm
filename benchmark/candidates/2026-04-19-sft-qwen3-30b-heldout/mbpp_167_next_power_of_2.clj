(defn next_power_of_2
  "	Write a cljthon function to find the smallest power of 2 greater than or equal to n."
  [n]
  (if (nil? n)
    nil
    (let [n (long n)]
      (if (<= n 1)
        1
        (let [p (bit-shift-left 1 (long (Math/floor (Math/log (double (dec n))) 2.0)))]
          (if (>= p n) p (bit-shift-left p 1)))))))