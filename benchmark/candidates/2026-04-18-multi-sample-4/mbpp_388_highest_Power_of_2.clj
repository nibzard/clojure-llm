(defn highest_Power_of_2
  " Write a cljthon function to find the highest power of 2 that is less than or equal to n."
  [n]
  (let [n (long (or n 0))]
    (if (pos? n)
      (loop [p 1]
        (if (> (* 2 p) n)
          p
          (recur (* 2 p))))
      0)))