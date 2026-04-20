(defn balanced-delims?
  "Return true if the expression contains balanced (), [], and {} delimiters.
Examples:
  (balanced-delims? \"([{}])\") => true
  (balanced-delims? \"([)]\") => false
  (balanced-delims? nil) => true"
  [exp])