(defn max-common-prefix
  "Return the longest common prefix of all strings in `words`.
  If `words` is empty, return \"\".
  If any word is nil, treat it as \"\".

  Examples:
  (max-common-prefix [\"flower\" \"flow\" \"flight\"]) ;=> \"fl\"
  (max-common-prefix [\"dog\" \"racecar\" \"car\"])     ;=> \"\"
  (max-common-prefix [])                             ;=> \"\"
  (max-common-prefix [nil \"abc\"])                   ;=> \"\""
  [words])