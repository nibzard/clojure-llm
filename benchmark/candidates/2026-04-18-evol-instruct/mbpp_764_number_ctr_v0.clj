(defn count-numeric-elements
  "Return the number of numeric elements in a collection.

  Counts integers, ratios, floats, and other numeric values.
  Nil values and non-numeric elements are ignored.

  Examples:
  (count-numeric-elements [1 \"2\" nil 3.5 :x]) => 2
  (count-numeric-elements '(1 2 3)) => 3
  (count-numeric-elements []) => 0"
  [coll]
  (count (filter number? coll)))