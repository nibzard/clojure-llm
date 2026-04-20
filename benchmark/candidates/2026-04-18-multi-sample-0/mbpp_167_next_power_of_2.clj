(defn next_power_of_2
  "	Write a cljthon function to find the smallest power of 2 greater than or equal to n."
  [n]
  (let [n (bigint (or n 0))]
    (loop [p 1N]
      (if (>= p n)
        p
        (recur (* 2N p))))))