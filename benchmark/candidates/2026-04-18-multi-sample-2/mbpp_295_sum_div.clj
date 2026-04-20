(defn sum_div
  "	Write a function to return the sum of all divisors of a number."
  [number]
  (let [n (cond
            (nil? number) 0
            (integer? number) (Math/abs (long number))
            :else (long (Math/abs number)))]
    (cond
      (zero? n) 0
      (= n 1) 1
      :else
      (let [limit (long (Math/sqrt n))]
        (loop [i 1
               acc 0]
          (if (> i limit)
            acc
            (if (zero? (mod n i))
              (let [q (quot n i)]
                (recur (inc i) (+ acc i (if (= i q) 0 q))))
              (recur (inc i) acc)))))))