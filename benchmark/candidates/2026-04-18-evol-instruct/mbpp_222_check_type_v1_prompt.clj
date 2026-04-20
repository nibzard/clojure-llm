(defn stable-partition
  "Return a vector of two vectors: [matches non-matches].

  Keep the original order of elements in each group.

  The predicate may be any boolean function. If the input collection is nil, return [[] []].

  Examples:
  (stable-partition odd? [1 2 3 4 5])  ;; => [[1 3 5] [2 4]]
  (stable-partition string? [\"a\" 1 nil \"b\"]) ;; => [[\"a\" \"b\"] [1 nil]]
  (stable-partition even? nil) ;; => [[] []]"
  [pred coll])