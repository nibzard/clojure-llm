(defn stable-sort-by
  "Return a stable sort of items by the given key function.

Examples:
(stable-sort-by count [\"bbb\" \"a\" \"cc\"]) ;=> [\"a\" \"cc\" \"bbb\"]
(stable-sort-by :age [{:name \"Ada\" :age 30} {:name \"Bob\" :age 25} {:name \"Eve\" :age 30}])
;=> [{:name \"Bob\" :age 25} {:name \"Ada\" :age 30} {:name \"Eve\" :age 30}]

Nil values are allowed and are sorted before non-nil values."
  [key-fn coll])