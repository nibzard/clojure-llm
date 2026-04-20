(defn equal-shape?
  "Return true if every collection in `colls` has the same count, otherwise false.

  Works with nil and empty collections.

  Examples:
  (equal-shape? [[1 2] [:a :b] '(x y)]) ;=> true
  (equal-shape? [[1] [2 3]])            ;=> false
  (equal-shape? [])                     ;=> true
  (equal-shape? [nil []])               ;=> true"
  [colls])