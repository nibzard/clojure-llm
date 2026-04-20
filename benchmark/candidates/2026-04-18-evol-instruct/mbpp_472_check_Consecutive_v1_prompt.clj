(defn consecutive-ranges
  "Return a lazy sequence of vectors describing consecutive runs in a collection of integers.

  Each run is a vector of consecutive numbers in the order they appear.
  Works with finite or infinite lazy sequences.

  Examples:
  (consecutive-ranges [1 2 3 5 6 8]) => ([1 2 3] [5 6] [8])
  (consecutive-ranges []) => ()
  (take 2 (consecutive-ranges (range))) => ([0 1 2 3 4 5 6 7 8 9 ...])"
  [xs])