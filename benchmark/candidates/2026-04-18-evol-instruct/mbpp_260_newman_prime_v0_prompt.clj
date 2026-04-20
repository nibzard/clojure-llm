(defn prime-partitions
  "Return a lazy sequence of all prime partitions of n.

  A prime partition is a list of primes in nondecreasing order whose sum is n.
  Return an empty sequence for n < 2.

  Examples:
  (prime-partitions 5) => ((2 3) (5))
  (prime-partitions 6) => ((2 2 2) (2 3))

  The result should be lazy and work for larger n without building unnecessary
  intermediate collections."
  [n])