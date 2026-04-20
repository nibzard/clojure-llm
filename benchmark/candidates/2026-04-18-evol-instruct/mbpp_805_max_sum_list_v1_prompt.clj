(defn longest-subseq
  "Return the longest subsequence from a collection of sequences.

  If multiple subsequences have the same maximum length, return the first one.
  Nil values inside subsequences should be preserved.

  Examples:
  (longest-subseq [[1 2] [3 4 5] [6]])
  => [3 4 5]

  (longest-subseq '((:a) (:b :c) (:d :e)))
  => (:b :c)"
  [seqs])