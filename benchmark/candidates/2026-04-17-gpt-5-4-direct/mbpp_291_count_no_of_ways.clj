(defn count_no_of_ways
  "	Write a function to find out the number of ways of painting the fence such that at most 2 adjacent posts have the same color for the given fence with n posts and k colors."
  [n k]
  (let [n (long (or n 0))
        k (long (or k 0))]
    (cond
      (<= n 0) 0
      (<= k 0) 0
      (= n 1) k
      (= n 2) (+ (* k k) 0)
      :else
      (loop [i 3
             same k
             diff (* k (dec k))]
        (if (> i n)
          (+ same diff)
          (let [new-same diff
                new-diff (* (+ same diff) (dec k))]
            (recur (inc i) new-same new-diff)))))))