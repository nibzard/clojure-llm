(defn same-non-nil-count?
  "Return true if all non-nil collections in coll have the same count.
  
  Nil values are ignored. An empty input or a collection containing only nils
  should return true.
  
  Examples:
  (same-non-nil-count? []) ;=> true
  (same-non-nil-count? [nil nil]) ;=> true
  (same-non-nil-count? [[1 2] nil [3 4]]) ;=> true
  (same-non-nil-count? [[1] [2 3]]) ;=> false"
  [coll])