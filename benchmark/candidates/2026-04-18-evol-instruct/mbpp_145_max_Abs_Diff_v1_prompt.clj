(defn max-abs-diff-in-order
  "Return the maximum absolute difference between two elements where the first element
  appears before the second element in the vector.

  Handles nil and short inputs by returning 0.

  Examples:
  (max-abs-diff-in-order [2 7 1 8]) => 7   ; |1 - 8|
  (max-abs-diff-in-order [10 3 6 1]) => 9   ; |10 - 1|
  (max-abs-diff-in-order nil) => 0
  (max-abs-diff-in-order [42]) => 0"
  [v])