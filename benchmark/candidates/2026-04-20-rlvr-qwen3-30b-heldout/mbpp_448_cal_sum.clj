(defn cal_sum
  "	Write a function to calculate the sum of perrin numbers."
  [n]
  (let [n (or n 0)]
    (cond
      (neg? n) 0
      (= n 0) 3
      (= n 1) 3
      (= n 2) 5
      :else
      (loop [i 3
             a 3
             b 0
             c 2
             total 5]
        (if (> i n)
          total
          (let [next-val (+ a b)]
            (recur (inc i) b c next-val (+ total next-val))))))))