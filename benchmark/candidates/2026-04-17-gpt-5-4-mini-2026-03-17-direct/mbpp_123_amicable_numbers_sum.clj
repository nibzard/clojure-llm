(defn amicable_numbers_sum
  "	Write a function to sum all amicable numbers from 1 to a specified number."
  [limit]
  (letfn [(proper-divisor-sum [n]
            (if (< n 2)
              0
              (let [root (long (Math/sqrt n))]
                (loop [i 2
                       sum 1]
                  (if (> i root)
                    sum
                    (if (zero? (mod n i))
                      (let [q (quot n i)]
                        (recur (inc i)
                               (if (= i q)
                                 (+ sum i)
                                 (+ sum i q))))
                      (recur (inc i) sum)))))))]
    (if (or (nil? limit) (< limit 1))
      0
      (let [limit (long limit)
            divisor-sums (into {} (map (fn [n] [n (proper-divisor-sum n)]) (range 1 (inc limit))))]
        (reduce
          (fn [acc n]
            (let [m (get divisor-sums n)]
              (if (and (some? m)
                       (not= n m)
                       (= n (proper-divisor-sum m)))
                (+ acc n)
                acc)))
          0
          (range 1 (inc limit)))))))