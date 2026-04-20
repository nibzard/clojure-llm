(defn prime-digit-sum
  "Given a collection of integers, return the sum of the digits of the largest prime number found.
  If there are no prime numbers, return 0.

  The input may be any seqable collection, including a lazy sequence.

  Examples:
  >>> (prime-digit-sum [0 3 2 1 3 5 7 4 5 5 5 2 181 32 4 32 3 2 32 324 4 3])
  10
  >>> (prime-digit-sum [1 0 1 8 2 4597 2 1 3 40 1 2 1 2 4 2 5 1])
  25
  >>> (prime-digit-sum (range 2 20))
  19
  >>> (prime-digit-sum [4 6 8 9 10])
  0"
  [coll]
  (let [prime? (fn [n]
                 (and (> n 1)
                      (not-any? #(zero? (mod n %))
                                (range 2 (inc (Math/sqrt n))))))
        digitsum (fn [n]
                   (reduce + (map #(Character/digit ^char % 10)
                                  (str n))))
        primes (filter prime? coll)]
    (if-let [m (seq primes)]
      (digitsum (apply max m))
      0)))