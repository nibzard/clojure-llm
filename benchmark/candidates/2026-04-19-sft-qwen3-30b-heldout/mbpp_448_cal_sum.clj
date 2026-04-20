(defn cal_sum
  "	Write a function to calculate the sum of perrin numbers."
  [n]
  (let [n (max 0 (or n 0))]
    (cond
      (zero? n) 0
      (= n 1) 2
      (= n 2) 3
      :else
      (loop [i 3
             a 2
             b 3
             c 2
             total 7]
        (if (> i n)
          total
          (let [next-val (+ a b)]
            (recur (inc i) b c next-val (+ total next-val))))))))