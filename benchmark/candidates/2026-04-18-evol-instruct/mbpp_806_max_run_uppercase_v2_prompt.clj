(defn max-run-of-true
  "Return the length of the longest contiguous run of true values in a collection.

  Examples:
  (max-run-of-true [true true false true]) => 2
  (max-run-of-true nil) => 0
  (max-run-of-true []) => 0"
  [coll])