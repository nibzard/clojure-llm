(defn amicable_numbers_sum
  "	Write a function to sum all amicable numbers from 1 to a specified number."
  [limit]
  (let [limit (long (or limit 0))]
    (if (< limit 2)
      0
      (letfn [(sum-proper-divisors [n]
                (cond
                  (= n 1) 0
                  :else
                  (let [root (long (Math/sqrt n))]
                    (loop [i 2
                           acc 1]
                      (if (> i root)
                        acc
                        (if (zero? (rem n i))
                          (let [q (quot n i)]
                            (recur (inc i)
                                   (+ acc i (if (= i q) 0 q))))
                          (recur (inc i) acc))))))]
        (reduce
         (fn [total a]
           (let [b (sum-proper-divisors a)]
             (if (and (not= a b)
                      (= a (sum-proper-divisors b)))
               (+ total a)
               total)))
         0
         (range 2 (inc limit)))))))