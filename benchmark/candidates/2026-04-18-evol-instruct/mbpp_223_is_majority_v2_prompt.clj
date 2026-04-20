(defn majority-by
  "Return the first item whose frequency is greater than half the collection size.
  Accepts any sequential collection, including nil and empty collections.
  
  Examples:
  (majority-by [1 2 2 2 3])   ;=> 2
  (majority-by [\"a\" \"b\" \"a\" \"a\"]) ;=> \"a\"
  (majority-by nil)          ;=> nil"
  [coll])