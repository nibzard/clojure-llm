(defn is_not_prime
  "Write a cljthon function to identify non-prime numbers."
  [n]
  (or (not (integer? n))
      (< n 2)
      (some #(zero? (rem n %)) (range 2 (inc (Math/sqrt n))))))