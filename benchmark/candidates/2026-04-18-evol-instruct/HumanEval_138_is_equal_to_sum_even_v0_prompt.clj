(defn can-partition-even-sum
  "Return true if the collection can be split into exactly two non-empty groups with equal sum.
  The function should work for vectors, lists, and lazy sequences.

  Examples:
  >>> (can-partition-even-sum [1 5 11 5])
  true
  >>> (can-partition-even-sum [1 2 3 5])
  false
  >>> (can-partition-even-sum [])
  false
  >>> (can-partition-even-sum (range 1 6))
  true"
  [coll])