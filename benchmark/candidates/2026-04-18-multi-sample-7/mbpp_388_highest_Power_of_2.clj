(defn highest_Power_of_2
  "	Write a cljthon function to find the highest power of 2 that is less than or equal to n."
  [n]
  (let [n (bigint (or n 0))]
    (if (pos? n)
      (loop [p 1N]
        (let [next-p (* 2N p)]
          (if (> next-p n)
            p
            (recur next-p))))
      0N)))