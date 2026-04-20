(defn balanced-delimiters?
  "Return true if the input string has balanced (), [], and {} delimiters.
  Ignores all non-delimiter characters.

  Examples:
  (balanced-delimiters? \"(a[b]{c})\") ;=> true
  (balanced-delimiters? \"([)]\")      ;=> false
  (balanced-delimiters? nil)           ;=> true"
  [s])