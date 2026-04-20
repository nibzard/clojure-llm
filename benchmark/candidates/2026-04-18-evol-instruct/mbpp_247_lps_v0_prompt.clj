(defn lps-nil-safe
  "Return the length of the longest palindromic subsequence in a sequence of characters.

  The input may be a string, a vector of characters, or nil.
  Return 0 for nil or an empty collection.

  Examples:
  (lps-nil-safe \"bbbab\") => 4
  (lps-nil-safe [\\a \\b \\c \\b \\a]) => 5
  (lps-nil-safe nil) => 0"
  [xs])