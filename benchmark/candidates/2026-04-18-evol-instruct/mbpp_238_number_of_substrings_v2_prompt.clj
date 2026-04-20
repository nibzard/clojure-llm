(defn count-distinct-subsequences
  "Write a Clojure function to count the number of distinct non-empty subsequences of a string.

  The input may be a string or nil. Return 0 for nil or the empty string.

  Examples:
  (count-distinct-subsequences \"abc\") => 7
  ;; \"a\" \"b\" \"c\" \"ab\" \"ac\" \"bc\" \"abc\"

  (count-distinct-subsequences \"aaa\") => 3
  ;; \"a\" \"aa\" \"aaa\"

  (count-distinct-subsequences nil) => 0"
  [s])