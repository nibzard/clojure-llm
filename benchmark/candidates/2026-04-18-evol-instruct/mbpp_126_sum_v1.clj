(defn common-divisors
  "Return a lazy sequence of all common divisors of two integers, in ascending order.

  Examples:
  (common-divisors 12 18) => (1 2 3 6)
  (common-divisors 7 13)  => (1)

  If either input is nil, return an empty sequence."
  [a b]
  (if (or (nil? a) (nil? b) (zero? a) (zero? b))
    ()
    (let [limit (min (Math/abs (long a)) (Math/abs (long b)))]
      (filter #(and (zero? (mod a %))
                    (zero? (mod b %)))
              (range 1 (inc limit))))))