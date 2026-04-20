(defn longest-prefix
  "Given a collection of strings, return the longest common prefix.

  If the collection is empty, return nil.
  If there is no shared prefix, return the empty string.

  Examples:
  (longest-prefix [\"flower\" \"flow\" \"flight\"]) ;=> \"fl\"
  (longest-prefix [\"dog\" \"racecar\" \"car\"])    ;=> \"\"
  (longest-prefix [])                             ;=> nil"
  [strings])