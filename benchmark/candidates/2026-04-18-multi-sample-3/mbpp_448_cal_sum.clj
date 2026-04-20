(defn cal_sum
  "	Write a function to calculate the sum of perrin numbers."
  [n]
  (let [n (long (or n 0))]
    (cond
      (<= n 0) 0
      (= n 1) 3
      (= n 2) 3
      (= n 3) 5
      :else
      (loop [i 4
             p0 3
             p1 0
             p2 2
             sum 5]
        (if (> i n)
          sum
          (let [p (+ p0 p1)]
            (recur (inc i) p1 p2 p (+ sum p))))))))