(defn max-infinite-prefix
  "Return the first prefix of `coll` whose sum is the highest seen so far.

  This variation works with lazy and even infinite sequences by only consuming
  as much as needed until it can determine the best prefix among the prefixes
  of the realized input.

  Examples:
  (max-infinite-prefix [1 2 -5 10 3])
  ;; => [1 2 -5 10 3]

  (max-infinite-prefix [5 -2 1 -1 4])
  ;; => [5]"
  [coll])