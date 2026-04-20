(defn occurrence-indices
  "Return a lazy sequence of all starting indices where `pattern` occurs in `coll`.
  Works with vectors, lists, and strings. Return nil if there is no match.

  Examples:
  (occurrence-indices [1 2 3 2 3 4] [2 3]) ;=> (1 3)
  (occurrence-indices \"abracadabra\" \"abra\") ;=> (0 7)
  (occurrence-indices [1 2 3] [4]) ;=> nil"
  [coll pattern])