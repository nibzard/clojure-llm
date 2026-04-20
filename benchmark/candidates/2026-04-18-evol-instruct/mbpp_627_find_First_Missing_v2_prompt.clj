(defn first-missing-positive
  "Return the smallest missing positive integer from a collection of numbers.

  The input may be unsorted and may contain duplicates, nils, or non-positive values.
  Works with any seqable collection.

  Examples:
  (first-missing-positive [3 4 -1 1]) ;=> 2
  (first-missing-positive [1 2 0])    ;=> 3
  (first-missing-positive [7 8 9])    ;=> 1"
  [xs])