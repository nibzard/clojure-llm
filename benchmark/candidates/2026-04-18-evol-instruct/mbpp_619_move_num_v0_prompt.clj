(defn move-num
  "Return a vector with all non-nil values first and all nil values moved to the end.

  Examples:
  (move-num [1 nil 2 nil 3]) => [1 2 3 nil nil]
  (move-num nil) => []
  (move-num []) => []"
  [xs])