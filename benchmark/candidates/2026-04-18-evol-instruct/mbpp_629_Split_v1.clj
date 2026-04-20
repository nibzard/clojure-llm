(defn even-indices
  "Return a vector of the elements at even indices (0, 2, 4, ...) from the given collection.

  Works with any sequential collection.

  Examples:
  (even-indices [\"a\" \"b\" \"c\" \"d\" \"e\"]) ;=> [\"a\" \"c\" \"e\"]
  (even-indices '(1 2 3 4 5 6))               ;=> [1 3 5]"
  [coll]
  (vec (keep-indexed (fn [idx x] (when (even? idx) x)) coll)))