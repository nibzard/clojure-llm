(defn max-run-of-true
  "Return the length of the longest consecutive run of truthy values in a collection.

  Examples:
  (max-run-of-true [false true true false true])
  ;=> 2

  (max-run-of-true [nil nil true true true false true])
  ;=> 3

  (max-run-of-true [])
  ;=> 0"
  [coll])