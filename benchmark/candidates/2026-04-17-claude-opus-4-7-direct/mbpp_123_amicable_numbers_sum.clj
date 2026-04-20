(defn amicable_numbers_sum
  "Write a function to sum all amicable numbers from 1 to a specified number."
  [limit]
  (letfn [(proper-divisors [n]
            (when (and (integer? n) (pos? n))
              (let [root (Math/sqrt n)
                    divisors (->> (range 1 (inc root))
                                  (filter #(zero? (rem n %)))
                                  (mapcat (fn [d]
                                            (let [other (/ n d)]
                                              (if (= d other)
                                                [d]
                                                [d other])))))]
                (reduce + 0 (disj (set divisors) n)))))]
    (let [sums (vec (map proper-divisors (range (inc limit))))
          amicable? (fn [n]
                      (let [s (get sums n)]
                        (and s
                             (not= n s)
                             (< s (count sums))
                             (= n (get sums s)))))]
      (reduce + 0 (filter amicable? (range 1 (inc limit)))))))