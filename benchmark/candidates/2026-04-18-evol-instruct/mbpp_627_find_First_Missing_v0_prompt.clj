(defn first-missing-odd
  "Return the smallest missing odd number from a sorted collection of integers.

  The input may contain duplicates, nil values, and negative numbers; ignore nils and
  consider only odd integers. If the collection contains all odd numbers in a contiguous
  sequence, return the next odd number after the last one.

  Examples:
  (first-missing-odd [1 3 5 9]) => 7
  (first-missing-odd [nil -3 -1 1 1 3 7]) => 5
  (first-missing-odd []) => 1"
  [nums])