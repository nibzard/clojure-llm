(defn split-even-odd
  "Return a vector of two vectors: the elements at even indexes and the elements at odd indexes.

  Works with any sequential collection, preserves order, and returns [[] []] for nil.

  Examples:
  (split-even-odd [1 2 3 4 5]) ;=> [[1 3 5] [2 4]]
  (split-even-odd \"abcd\")     ;=> [\\a \\c \\e?] ... no, for strings, treat as seqable collection of characters
  (split-even-odd nil)         ;=> [[] []]"
  [coll])