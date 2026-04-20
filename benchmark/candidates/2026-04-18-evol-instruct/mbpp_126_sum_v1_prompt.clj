(defn common-divisors
  "Return a lazy sequence of all common divisors of two integers, in ascending order.

  Examples:
  (common-divisors 12 18) => (1 2 3 6)
  (common-divisors 7 13)  => (1)

  If either input is nil, return an empty sequence."
  [a b])