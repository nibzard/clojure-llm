(defn occurrence-indices
  "Return a lazy sequence of all starting indices where `pattern` appears in `coll`.
  Works on any sequential collection, not just strings.

  Examples:
  (occurrence-indices [1 2 3 1 2 4] [1 2])
  => (0 3)

  (occurrence-indices \"banana\" \"ana\")
  => (1 3)

  Return nil if there are no matches."
  [coll pattern])