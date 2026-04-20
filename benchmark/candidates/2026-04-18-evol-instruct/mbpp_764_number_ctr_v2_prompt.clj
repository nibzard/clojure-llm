(defn number-ctr
  "Count the number of numeric elements in a collection.

  Works with vectors, lists, lazy sequences, and nil.

  Examples:
  (number-ctr [1 2 :a 3])     ;=> 3
  (number-ctr '(\"x\" 4 5 nil)) ;=> 2
  (number-ctr nil)            ;=> 0"
  [coll])