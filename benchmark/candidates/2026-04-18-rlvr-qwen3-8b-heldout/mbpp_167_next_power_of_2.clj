(defn next_power_of_2
  "	Write a cljthon function to find the smallest power of 2 greater than or equal to n."
  [n]
  (cond
    (nil? n) nil
    (<= n 0) nil
    (integer? n) (loop [p 1]
                   (if (>= p n)
                     p
                     (recur (* p 2))))
    :else nil))