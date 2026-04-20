(defn skjkasdkd
  [lst]
  (let [prime? (fn [n]
                 (and (>= n 2)
                      (not-any? #(zero? (mod n %))
                                (range 2 (inc (long (Math/sqrt n)))))))
        primes (filter prime? lst)]
    (when (seq primes)
      (let [max-prime (apply max primes)]
        (reduce + (map #(- (int %) 48) (str max-prime)))))))