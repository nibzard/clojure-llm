(defn octagonal-at
  "Return the nth octagonal number from a sequence of values.

  Accepts any sequence type, including lazy and infinite sequences.

  Examples:
  (octagonal-at [1 8 21 40 65] 3) ;=> 40
  (octagonal-at (iterate inc 1) 5) ;=> 5"
  [coll n]
  (nth coll (dec n)))