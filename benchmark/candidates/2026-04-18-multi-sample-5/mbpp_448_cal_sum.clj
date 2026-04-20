(defn cal_sum
  "	Write a function to calculate the sum of perrin numbers."
  [n]
  (let [n (or n 0)]
    (cond
      (<= n 0) 0
      (= n 1) 3
      (= n 2) 3
      :else
      (loop [i 3
             a 3
             b 0
             c 2
             sum 5]
        (if (= i n)
          sum
          (let [p (+ a b)]
            (recur (inc i) b c p (+ sum p))))))))