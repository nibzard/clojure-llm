(defn replace-nth
  "Return a new vector with the element at index n replaced by x.

  Examples:
  (replace-nth [1 2 3] 1 9) => [1 9 3]
  (replace-nth [:a :b :c] 0 :z) => [:z :b :c]

  If n is out of range, return the original vector unchanged."
  [v n x])