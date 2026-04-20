(defn amicable_numbers_sum
  "Write a function to sum all amicable numbers from 1 to a specified number."
  [limit]
  (letfn [(sum-divisors [n]
            (if (< n 2)
              0
              (let [sqrt-n (Math/sqrt n)]
                (loop [i 2
                       sum 1]
                  (if (> i sqrt-n)
                    sum
                    (if (zero? (rem n i))
                      (let [q (quot n i)]
                        (recur (inc i) (+ sum i (if (= i q) 0 q))))
                      (recur (inc i) sum)))))))]
    (reduce +
            (filter (fn [a]
                      (let [b (sum-divisors a)]
                        (and (not= a b)
                             (= a (sum-divisors b)))))
                    (range 1 (inc limit))))))