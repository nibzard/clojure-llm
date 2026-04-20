(defn prime-index-sum
  "Given a collection of integers, return the sum of the digits of the
  first prime number found at a prime index, using 0-based indexing.

  If no prime value exists at a prime index, return 0.

  The input may be any seqable collection, including lazy sequences.

  Examples:
  >>> (prime-index-sum [4 6 8 9 11 12 13 14 15])
  2
  ; indices 2,3,5,7 are prime; values are 8,9,12,14, so no prime values -> 0

  >>> (prime-index-sum [1 2 3 4 5 6 7 8 9])
  5
  ; index 2 has value 3, which is prime, digit sum = 3

  >>> (prime-index-sum (range))
  2
  ; index 2 has value 2, first prime at prime index

  >>> (prime-index-sum [0 1 2 4 6 8 10 11 13])
  2"
  [xs]
  (letfn [(prime? [n]
            (and (> n 1)
                 (not-any? #(zero? (mod n %))
                           (range 2 (inc (Math/floor (Math/sqrt n)))))))
          (digit-sum [n]
            (reduce + (map #(Character/digit % 10) (str n))))]
    (or (some (fn [[idx v]]
                (when (and (prime? idx) (prime? v))
                  (digit-sum v)))
              (map-indexed vector xs))
        0)))