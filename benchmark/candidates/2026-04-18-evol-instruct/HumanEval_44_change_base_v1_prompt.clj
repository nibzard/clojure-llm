(defn change-base-seq
  "Convert a non-negative integer x into a sequence of digits in the given base.
  The digits are returned in most-significant to least-significant order.
  Base must be between 2 and 10.

  Examples:
  >>> (change-base-seq 8 3)
  [2 2]
  >>> (change-base-seq 8 2)
  [1 0 0 0]
  >>> (change-base-seq 7 2)
  [1 1 1]
  >>> (change-base-seq 0 5)
  [0]"
  [x base])