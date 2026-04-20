(defn count-nonempty-subsequences
  "Return the number of non-empty subsequences of a collection.

  A subsequence preserves order but does not need to be contiguous.
  Return 0 for nil or an empty collection.

  Examples:
  (count-nonempty-subsequences [1 2 3]) => 7
  (count-nonempty-subsequences '(:a :b)) => 3
  (count-nonempty-subsequences nil) => 0"
  [coll]
  (if (seq coll)
    (dec (bit-shift-left 1 (count coll)))
    0))