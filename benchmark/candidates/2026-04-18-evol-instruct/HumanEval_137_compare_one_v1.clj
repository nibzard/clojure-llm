(defn merge-odd-indexed
  "Create a function that takes a vector and returns a new vector containing only the elements at odd indexes.
  If the input is nil, return an empty vector.
  The function should work with finite or infinite sequences, and preserve the original order.
  Examples:
  >>> (merge-odd-indexed [1 2 3 4 5])
  [2 4]
  >>> (merge-odd-indexed [\"a\" \"b\" \"c\" \"d\"])
  [\"b\" \"d\"]
  >>> (merge-odd-indexed nil)
  []"
  [v]
  (vec (keep-indexed (fn [i x] (when (odd? i) x)) (or v []))))