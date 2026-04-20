(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (Math/abs (long number))]
    (cond
      (zero? n) 0
      (= n 1) 1
      :else
      (loop [i 1
             sum 0]
        (if (> (* i i) n)
          sum
          (if (zero? (mod n i))
            (let [j (quot n i)]
              (recur (inc i)
                     (if (= i j)
                       (+ sum i)
                       (+ sum i j))))
            (recur (inc i) sum)))))))