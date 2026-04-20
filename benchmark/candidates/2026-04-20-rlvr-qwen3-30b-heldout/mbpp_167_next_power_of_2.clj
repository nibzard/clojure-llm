(defn next_power_of_2
  "	Write a cljthon function to find the smallest power of 2 greater than or equal to n."
  [n]
  (if (nil? n)
    nil
    (let [n (long n)]
      (if (<= n 1)
        1
        (loop [x n]
          (let [y (bit-shift-right x 1)]
            (if (zero? y)
              (bit-shift-left 1 (long (Math/log n)))
              (recur y))))))))