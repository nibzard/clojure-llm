(defn proper-divisor-sum
  "Return the sum of the proper divisors of n (all divisors less than n).
  If n is nil or less than 2, return 0.

  Examples:
  (proper-divisor-sum 1)   ;=> 0
  (proper-divisor-sum 6)   ;=> 6  ;; 1 + 2 + 3
  (proper-divisor-sum 12)  ;=> 16  ;; 1 + 2 + 3 + 4 + 6"
  [n]
  (if (and (some? n) (>= n 2))
    (reduce +
            (filter #(zero? (mod n %))
                    (range 1 n)))
    0))