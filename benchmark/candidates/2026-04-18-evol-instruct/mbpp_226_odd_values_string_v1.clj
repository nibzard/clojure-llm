(defn even-indexed-chars
  "Return a string containing only the characters at even indexes (0, 2, 4, ...).

  Works with nil by returning an empty string.

  Examples:
  (even-indexed-chars \"abcdef\") ;=> \"ace\"
  (even-indexed-chars \"a\")      ;=> \"a\"
  (even-indexed-chars nil)       ;=> \"\""
  [s]
  (apply str (keep-indexed (fn [i ch] (when (even? i) ch)) (or s ""))))