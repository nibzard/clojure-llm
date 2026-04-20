(defn k_smallest_sums
  "Return the k smallest pair sums from two sequences, as a lazy sequence of maps.

  Each result is a map of the form {:a x :b y :sum s}, where x comes from xs,
  y comes from ys, and s is their sum.

  The function must work with infinite sequences and should not realize more
  elements than needed.

  Examples:
  (take 3 (k_smallest_sums [1 2] [10 20] 3))
  => ({:a 1, :b 10, :sum 11}
      {:a 1, :b 20, :sum 21}
      {:a 2, :b 10, :sum 12})"
  [xs ys k])