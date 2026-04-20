(defn problem-prime-numbers [a]
  (letfn [(prime? [n]
            (and (> n 1)
                 (not-any? #(zero? (mod n %))
                           (range 2 (inc (int (Math/sqrt n)))))))]
    (loop [n 2
           primes []]
      (if (= (count primes) a)
        primes
        (recur (inc n) (if (prime? n) (conj primes n) primes))))))