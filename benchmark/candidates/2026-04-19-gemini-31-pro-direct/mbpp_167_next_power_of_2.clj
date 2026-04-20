(defn next_power_of_2
  "Write a clojure function to find the smallest power of 2 greater than or equal to n."
  [n]
  (if (<= n 1)
    1
    (loop [p 1]
      (if (>= p n)
        p
        (recur (*' 2 p))))))