(defn min-width-sublist
  "Return the shortest sublist in `coll` that contains at least one occurrence of every distinct element in `coll`.

  If `coll` is nil or empty, return nil.

  Examples:
  (min-width-sublist [1 2 1 3 2])
  ;; => [1 2 1 3]

  (min-width-sublist '(\"a\" \"b\" \"a\"))
  ;; => (\"a\" \"b\" \"a\")"
  [coll])