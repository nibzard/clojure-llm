(defn sum
  "Write a cljthon function to find the sum of common divisors of two given numbers."
  [a b]
  (letfn [(gcd [x y]
            (if (zero? y)
              (Math/abs x)
              (recur y (mod x y))))]
    (let [g (gcd a b)]
      (reduce + (filter #(zero? (mod g %)) (range 1 (inc g)))))))