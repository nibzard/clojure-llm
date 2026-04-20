(defn common-subsequence
  "Return the longest common subsequence of three sequences.

  The inputs may be strings or vectors. Return the result as a vector of
  elements from the sequences. If any input is nil, treat it as an empty sequence.

  Examples:
  (common-subsequence \"abcdef\" \"acbcf\" \"abcf\") ;=> [\\a \\b \\c \\f]
  (common-subsequence [1 2 3 4] [2 4 3 1] [5 2 3 4]) ;=> [2 3]"
  [a b c])