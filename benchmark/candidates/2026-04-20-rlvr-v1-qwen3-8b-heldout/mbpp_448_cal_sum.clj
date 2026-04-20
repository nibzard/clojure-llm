(defn cal_sum
  "	Write a function to calculate the sum of perrin numbers."
  [n]
  (let [n (max 0 (long (or n 0)))]
    (cond
      (<= n 0) 0
      (= n 1) 3
      (= n 2) 0
      :else
      (loop [i 3
             a 3
             b 0
             c 2
             sum 3]
        (if (> i n)
          sum
          (let [next-val (+ a b c)
                new-sum (+ sum next-val)]
            (recur (inc i) b c next-val new-sum)))))))