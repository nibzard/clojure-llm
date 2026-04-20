(defn next_power_of_2
  "	Write a cljthon function to find the smallest power of 2 greater than or equal to n."
  [n]
  (let [n (long (Math/abs (double n)))]
    (if (zero? n)
      1
      (loop [p 1]
        (if (>= p n)
          p
          (recur (* p 2)))))))