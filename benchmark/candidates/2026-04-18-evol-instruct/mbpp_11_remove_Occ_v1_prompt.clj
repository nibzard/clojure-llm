(defn remove-nth-occurrence
  "Return a string with the n-th occurrence of ch removed from s.
  If n is nil or there are fewer than n occurrences, return s unchanged.

  Examples:
  (remove-nth-occurrence \"banana\" \\a 2) => \"banaa\"
  (remove-nth-occurrence \"banana\" \\a 1) => \"bnana\"
  (remove-nth-occurrence \"banana\" \\x 1) => \"banana\""
  [s ch n])