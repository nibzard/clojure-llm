(defn next_power_of_2
  "	Write a cljthon function to find the smallest power of 2 greater than or equal to n."
  [n]
  (if (<= n 0)
    1
    (loop [p 1]
      (if (>= p n)
        p
        (recur (* p 2))))))