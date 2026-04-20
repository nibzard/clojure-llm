(defn cal_sum
  "Write a function to calculate the sum of perrin numbers."
  [n]
  (if (<= n 0)
    0
    (loop [i 2
           p0 3
           p1 0
           p2 2
           sum 5] ;; Sum of first 3 Perrin numbers (3, 0, 2)
      (if (>= i n)
        sum
        (let [p3 (+ p0 p1)]
          (recur (inc i) p1 p2 p3 (+ sum p3)))))))