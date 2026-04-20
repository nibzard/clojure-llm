(defn merge-vectors
  "Return a single vector containing all elements from the provided vectors, in order.

  Examples:
  (merge-vectors [1 2] [3 4]) ;=> [1 2 3 4]
  (merge-vectors [] [\"a\" \"b\"]) ;=> [\"a\" \"b\"]
  (merge-vectors nil [1 2]) ;=> [1 2]"
  [v1 v2]
  (vec (concat (or v1 []) (or v2 []))))