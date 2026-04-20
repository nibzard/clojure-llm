(defn unique-preserving-last
  "Return a vector of the distinct values in `coll`, keeping their last
  occurrence order.

  Examples:
  (unique-preserving-last [1 2 3 2 4 1]) ;=> [3 2 4 1]
  (unique-preserving-last nil) ;=> []
  (unique-preserving-last '(\"a\" \"b\" \"a\" \"c\" \"b\")) ;=> [\"a\" \"c\" \"b\"]"
  [coll])