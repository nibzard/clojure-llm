(defn sumofFactors
  "Write a cljthon function to find the sum of even factors of a number."
  [n]
  (when (and (integer? n) (pos? n))
    (let [limit (int (Math/sqrt n))]
      (loop [i 2
             sum 0]
        (if (> i limit)
          sum
          (let [rem (mod n i)]
            (if (zero? rem)
              (let [factor1 i
                    factor2 (quot n i)]
                (if (= factor1 factor2)
                  (if (even? factor1)
                    (recur (inc i) (+ sum factor1))
                    (recur (inc i) sum))
                  (recur (inc i) (+ sum 
                                    factor1 
                                    (if (even? factor2) factor2 0)))))
              (recur (inc i) sum))))))))