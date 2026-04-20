(defn split-with-delim
  "Write a function that splits a string on a delimiter character and returns a vector of substrings.

  Examples:
  (split-with-delim \"a,b,c\" \\,) => [\"a\" \"b\" \"c\"]
  (split-with-delim \"hello\" \\,) => [\"hello\"]
  (split-with-delim \"a,,b\" \\,) => [\"a\" \"\" \"b\"]
  (split-with-delim nil \\,) => []"
  [s delim])