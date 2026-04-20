(defn max-run
  "Return the length of the longest contiguous run of truthy values in a sequence.

  Examples:
  (max-run [false true true false true]) => 2
  (max-run [1 2 3]) => 3
  (max-run []) => 0
  (max-run nil) => 0"
  [xs])