(defn remove-nested
  "Return a flat vector with all non-vector elements from the input collection.

  Examples:
  (remove-nested [1 [2 3] 4]) => [1 4]
  (remove-nested nil) => []
  (remove-nested '([:a] :b [c])) => [:b]

  Works with any sequential collection and preserves order."
  [coll]
  (vec (remove vector? coll)))