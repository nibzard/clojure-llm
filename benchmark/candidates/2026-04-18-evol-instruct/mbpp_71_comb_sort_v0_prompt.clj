(defn stable-sort-by
  "Return a stable sorted sequence of the given items using the provided key function.
  Preserve the original relative order of items with equal keys.

  Examples:
  (stable-sort-by count [\"bb\" \"a\" \"cc\" \"d\"]) ;=> (\"a\" \"d\" \"bb\" \"cc\")
  (stable-sort-by :age [{:name \"A\" :age 2} {:name \"B\" :age 1} {:name \"C\" :age 2}])
  ;=> ({:name \"B\" :age 1} {:name \"A\" :age 2} {:name \"C\" :age 2})"
  [key-fn items])