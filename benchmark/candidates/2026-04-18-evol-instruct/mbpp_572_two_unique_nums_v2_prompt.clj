(defn unique-in-order
  "Return a lazy sequence of the items from coll with consecutive duplicates removed.

  Works on any sequential collection, preserves the first item of each run,
  and returns nil for nil input.

  Examples:
  (unique-in-order [1 1 2 2 3 1 1]) ;=> (1 2 3 1)
  (unique-in-order \"aaabcca\")      ;=> (\\a \\b \\c \\a)
  (unique-in-order nil)             ;=> nil"
  [coll])