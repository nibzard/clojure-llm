(defn is_prime
  "Return true if a given number is prime, and false otherwise."
  [n]
  (if (<= n 1)
    false
    (not-any? #(zero? (rem n %))
              (range 2 (inc (int (Math/sqrt n)))))))