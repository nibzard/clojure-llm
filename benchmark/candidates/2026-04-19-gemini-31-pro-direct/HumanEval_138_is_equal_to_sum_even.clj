(defn is_equal_to_sum_even
  "Evaluate whether the given number n can be written as the sum of exactly 4 positive even numbers"
  [n]
  (and (even? n) (>= n 8)))