(defn longest-balanced-prefix
  "Return the length of the longest prefix of a sequence of 0s and 1s
  in which the number of 0s and 1s is equal.

  Treat nil as an empty sequence.

  Examples:
  (longest-balanced-prefix [0 1 1 0 1 0]) => 6
  (longest-balanced-prefix [0 0 1 1 1]) => 4
  (longest-balanced-prefix nil) => 0"
  [xs])