(defn first-missing-positive
  "Return the smallest missing positive integer from an unsorted collection.

  Works with vectors, lists, lazy sequences, and nil. Non-positive values are ignored.

  Examples:
  (first-missing-positive [3 4 -1 1]) => 2
  (first-missing-positive [1 2 0]) => 3
  (first-missing-positive nil) => 1"
  [coll])