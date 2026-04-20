(defn balanced-delimiters?
  "Return true if a string or sequence has balanced (), {}, and [] delimiters.
  Ignores all non-delimiter characters.

  Examples:
  (balanced-delimiters? \"(a+[b*c]-{d/e})\") => true
  (balanced-delimiters? \"([)]\") => false
  (balanced-delimiters? nil) => true"
  [x])