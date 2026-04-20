(defn longest-common-prefix
  "Return the longest common prefix of three strings.

  Treat nil as an empty string.

  Examples:
  (longest-common-prefix \"flower\" \"flow\" \"flight\") ;=> \"fl\"
  (longest-common-prefix \"abc\" \"abc\" \"abc\")        ;=> \"abc\"
  (longest-common-prefix nil \"abc\" \"ab\")            ;=> \"\""
  [a b c])