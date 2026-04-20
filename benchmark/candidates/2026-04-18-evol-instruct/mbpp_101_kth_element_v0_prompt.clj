(defn kth-infinite
  "Return the kth element of a sequence using 1-based indexing.

  Works with finite or infinite sequences. If k is out of range or not positive,
  return nil.

  Examples:
  (kth-infinite [10 20 30] 2) => 20
  (kth-infinite (range) 5)      => 4"
  [coll k])