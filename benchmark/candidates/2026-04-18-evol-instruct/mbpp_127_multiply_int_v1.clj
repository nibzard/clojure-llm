(defn multiply-collection
  "Return the product of all numeric values in a collection.

  Treat nil as an empty collection, returning 1.
  Ignore non-numeric values.

  Examples:
  (multiply-collection [2 3 4]) => 24
  (multiply-collection [2 nil 5 \"x\" 3]) => 30
  (multiply-collection nil) => 1"
  [coll]
  (reduce * 1 (filter number? (or coll []))))