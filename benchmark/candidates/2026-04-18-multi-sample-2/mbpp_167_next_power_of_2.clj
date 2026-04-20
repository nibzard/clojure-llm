(defn next_power_of_2
  "	Write a cljthon function to find the smallest power of 2 greater than or equal to n."
  [n]
  (let [n (long (or n 0))]
    (cond
      (<= n 1) 1
      :else
      (loop [p 1]
        (if (>= p n)
          p
          (recur (* 2 p)))))))