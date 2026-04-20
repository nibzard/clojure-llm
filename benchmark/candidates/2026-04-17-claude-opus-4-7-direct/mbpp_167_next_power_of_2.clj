(defn next_power_of_2
  "Write a cljthon function to find the smallest power of 2 greater than or equal to n."
  [n]
  (if (<= n 0)
    1
    (let [x (long n)]
      (loop [x x
             p 1]
        (if (>= p x)
          p
          (recur x (bit-shift-left p 1)))))))