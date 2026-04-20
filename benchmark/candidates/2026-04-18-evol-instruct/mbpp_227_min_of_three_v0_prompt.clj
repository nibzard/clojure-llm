(defn min-by-key
  "Return the item in a collection with the smallest value of (key-fn item).
If the collection is empty, return nil.

Examples:
(min-by-key count [\"pear\" \"apple\" \"kiwi\"]) ;=> \"kiwi\"
(min-by-key :age [{:name \"Ada\" :age 36} {:name \"Bob\" :age 29}]) ;=> {:name \"Bob\" :age 29}
(min-by-key :score []) ;=> nil"
  [key-fn coll])