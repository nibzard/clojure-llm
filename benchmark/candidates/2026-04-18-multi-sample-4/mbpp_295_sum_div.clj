(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (cond
            (nil? number) 0
            (number? number) (long (Math/abs number))
            :else 0)]
    (cond
      (zero? n) 0
      :else
      (loop [i 1
             total 0]
        (if (> i n)
          total
          (recur (inc i)
                 (if (zero? (mod n i))
                   (+ total i)
                   total)))))))