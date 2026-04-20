(defn is_strictly_monotonic
  "Return true if the collection is strictly monotonic (entirely increasing or entirely decreasing).
  A collection with fewer than 2 elements is considered strictly monotonic.

  Examples:
  (is_strictly_monotonic [1 2 3])   ;=> true
  (is_strictly_monotonic [3 2 1])   ;=> true
  (is_strictly_monotonic [1 1 2])   ;=> false
  (is_strictly_monotonic nil)      ;=> true"
  [xs])