(defn count-unique-seq
  "Given a sequence of values, return the number of distinct non-nil values.
  Preserve laziness-friendly behavior: the input may be an infinite sequence, so only consume as much as needed.
  Return 0 for nil or an empty sequence.

  Examples:
  >>> (count-unique-seq [1 2 2 3 3 3])
  3
  >>> (count-unique-seq [nil :a nil :b :a])
  2
  >>> (count-unique-seq nil)
  0"
  [xs]
  (if (seq xs)
    (count (distinct (remove nil? xs)))
    0))