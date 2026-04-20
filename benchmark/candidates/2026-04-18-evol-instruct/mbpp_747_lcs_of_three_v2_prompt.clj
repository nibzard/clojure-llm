(defn lcs-of-seqs
  "Return the longest common subsequence shared by three sequences.

  The inputs may be any sequential collections (not just strings), including
  vectors and lists. Return the result as a vector.

  Examples:
  (lcs-of-seqs [1 2 3 4] [2 4 3 1] [2 3 4]) ;=> [2 3]
  (lcs-of-seqs \"abcde\" \"ace\" \"aec\")     ;=> [\\a \\e]
  (lcs-of-seqs [] [1 2] [1])                ;=> []"
  [a b c])