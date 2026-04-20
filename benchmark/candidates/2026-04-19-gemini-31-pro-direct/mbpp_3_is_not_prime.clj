(defn is_not_prime
  "Write a cljthon function to identify non-prime numbers."
  [n]
  (if (< n 2)
    true
    (boolean (some #(zero? (mod n %)) (range 2 (inc (int (Math/sqrt n))))))))