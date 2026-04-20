(defn sum-of-divisors-up-to
  "Return the sum of all positive divisors of n that are less than or equal to limit.
  
  Examples:
  (sum-of-divisors-up-to 12 6) => 16 ; divisors 1, 2, 3, 4, 6
  (sum-of-divisors-up-to 12 3) => 6  ; divisors 1, 2, 3
  (sum-of-divisors-up-to 7 10) => 8  ; divisors 1, 7
  "
  [n limit]
  (->> (range 1 (inc (min n limit)))
       (filter #(zero? (mod n %)))
       (reduce + 0)))