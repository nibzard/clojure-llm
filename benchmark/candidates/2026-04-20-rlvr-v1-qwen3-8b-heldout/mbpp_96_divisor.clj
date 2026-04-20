(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (long (Math/abs (long n)))]
    (if (zero? n)
      0
      (count (filter #(zero? (mod n %)) (range 1 (inc n)))))))