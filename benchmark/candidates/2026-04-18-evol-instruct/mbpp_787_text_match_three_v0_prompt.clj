(defn vector_match_three
  "Return true if the vector contains an :a element immediately followed by three :b elements.

Examples:
  (vector_match_three [:x :a :b :b :b :y]) => true
  (vector_match_three [:a :b :b]) => false
  (vector_match_three nil) => false"
  [v])