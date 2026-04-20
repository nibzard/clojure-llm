(defn strip-nested-maps
  "Return a vector with all map values removed from the input vector, preserving order.

  Examples:
  (strip-nested-maps [1 {:a 2} 3 {:b 4}]) => [1 3]
  (strip-nested-maps []) => []
  (strip-nested-maps nil) => []"
  [xs])