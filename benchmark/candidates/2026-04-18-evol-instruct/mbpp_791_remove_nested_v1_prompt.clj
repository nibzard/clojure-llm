(defn remove_nested
  "Return a vector with any nested vectors removed, preserving only top-level non-vector values.

  Examples:
  (remove_nested [1 [2 3] 4 [5] :a]) => [1 4 :a]
  (remove_nested []) => []
  (remove_nested [nil [1] false]) => [nil false]"
  [test-tup])