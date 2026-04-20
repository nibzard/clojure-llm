(defn add-string
  "Return a lazy sequence of strings by applying a format string to each element of a possibly infinite collection.

  The format string should use `format` syntax, e.g. \"[%s]\" or \"%04d\".
  Return an empty sequence when the input is nil.

  Examples:
  (add-string \"<%s>\" [1 2 3]) ;=> (\"<1>\" \"<2>\" \"<3>\")
  (take 3 (add-string \"%02d\" (range))) ;=> (\"00\" \"01\" \"02\")"
  [fmt coll])