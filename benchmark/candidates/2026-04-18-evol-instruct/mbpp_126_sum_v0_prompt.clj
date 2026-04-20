(defn sum-common-divisors
  "Return the sum of all values that appear in both collections.
  Treat nil as an empty collection.

  Examples:
  (sum-common-divisors [1 2 3 4] [3 4 5]) ;=> 7
  (sum-common-divisors nil [1 2 3])       ;=> 0
  (sum-common-divisors '(1 2 2 3) [2 2 4]) ;=> 4"
  [a b])