(defn amicable_numbers_sum
  "	Write a function to sum all amicable numbers from 1 to a specified number."
  [limit]
  (let [limit (if (number? limit) (long limit) 0)]
    (if (<= limit 1)
      0
      (letfn [(sum-proper-divisors [n]
                (cond
                  (<= n 1) 0
                  :else
                  (loop [i 2
                         total 1]
                    (if (> (* i i) n)
                      total
                      (if (zero? (mod n i))
                        (let [q (quot n i)]
                          (recur (inc i)
                                 (+ total
                                    i
                                    (if (= i q) 0 q))))
                        (recur (inc i) total))))))]
        (transduce
         (filter (fn [a]
                   (let [b (sum-proper-divisors a)]
                     (and (not= a b)
                          (= a (sum-proper-divisors b))))))
         +
         (range 2 (inc limit)))))))