(defn count-positive-bits
  "Return the number of positive integers in a collection.

  Handles nil and lazy sequences.
  Examples:
  (count-positive-bits [1 -2 3 0 4]) => 3
  (count-positive-bits nil) => 0
  (count-positive-bits (range -2 3)) => 2"
  [xs])