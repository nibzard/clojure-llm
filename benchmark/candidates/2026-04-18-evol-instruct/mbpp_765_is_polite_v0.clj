(defn polite-indexes
  "Return a lazy sequence of all polite numbers up to n, inclusive.

  A polite number is a positive integer that can be expressed as the sum of
  two or more consecutive positive integers.

  Examples:
  (polite-indexes 15) => (3 5 6 7 9 10 12 15)
  (polite-indexes 1)  => ()"
  [n]
  (letfn [(odd-divisors [x]
            (filter odd? (range 1 (inc x))))
          (polite? [x]
            (boolean
             (some (fn [d]
                     (and (odd? d)
                          (> d 1)
                          (zero? (mod x d))))
                   (odd-divisors x))))]
    (filter polite? (range 1 (inc n)))))