(defn amicable_numbers_sum
  "	Write a function to sum all amicable numbers from 1 to a specified number."
  [limit]
  (let [limit (or limit 0)]
    (if (<= limit 1)
      0
      (let [sum-proper-divisors
            (fn [n]
              (if (= n 1)
                0
                (let [root (long (Math/sqrt n))]
                  (loop [i 2
                         acc 1]
                    (if (> i root)
                      acc
                      (if (zero? (mod n i))
                        (let [q (quot n i)]
                          (recur (inc i)
                                 (+ acc i (if (= i q) 0 q))))
                        (recur (inc i) acc)))))))]
        (->> (range 2 limit)
             (filter (fn [a]
                       (let [b (sum-proper-divisors a)]
                         (and (not= a b)
                              (= a (sum-proper-divisors b))))))
             (reduce + 0))))))