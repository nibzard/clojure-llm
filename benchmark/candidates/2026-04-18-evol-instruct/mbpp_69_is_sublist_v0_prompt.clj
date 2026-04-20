(defn subvec?
  "Return true if vector `v` contains vector `pattern` as a contiguous subsequence.

  Examples:
  (subvec? [1 2 3 4] [2 3]) => true
  (subvec? [1 2 3 4] []) => true
  (subvec? [] [1]) => false

  The function should work with nil inputs by treating nil as an empty vector."
  [v pattern])