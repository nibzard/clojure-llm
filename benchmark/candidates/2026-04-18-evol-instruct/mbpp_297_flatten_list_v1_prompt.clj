(defn deep-flatten
  "Return a flattened sequence of all non-collection values in a nested structure.

  Works on lists, vectors, and lazy sequences. Nil values are ignored.

  Examples:
  (deep-flatten [1 [2 nil] '(3 [4 5])])
  => (1 2 3 4 5)

  (deep-flatten nil)
  => ()"
  [x])