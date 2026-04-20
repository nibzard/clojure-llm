(defn unique-by
  "Return the first occurrence of each item, grouped by a key function, preserving input order.

  Examples:
  >>> (unique-by identity [5 3 5 2 3 3 9 0 123])
  [5 3 2 9 0 123]

  >>> (unique-by count [1 22 3 44 55 6])
  [1 22]

  Returns an empty vector for nil or an empty collection."
  [f coll])