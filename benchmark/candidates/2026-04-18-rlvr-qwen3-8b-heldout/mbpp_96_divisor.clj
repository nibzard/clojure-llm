(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (if (number? n) (long n) 0)]
    (if (zero? n)
      0
      (let [root (long (Math/sqrt n))]
        (if (= (* root root) n)
          (+ 1 (count (filter #(zero? (mod n %)) (range 1 (dec root)))))
          (count (filter #(zero? (mod n %)) (range 1 (inc root)))))))))