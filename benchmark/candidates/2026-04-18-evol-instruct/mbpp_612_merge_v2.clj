(defn merge-by-index
  "Given a vector of vectors, each containing exactly two elements, return a vector of two vectors:
  the first contains all first elements, and the second contains all second elements.

  Handles an empty input by returning [[] []].

  Examples:
  (merge-by-index [[1 2] [3 4] [5 6]])
  => [[1 3 5] [2 4 6]]

  (merge-by-index [])
  => [[] []]"
  [pairs]
  (if (seq pairs)
    [(mapv first pairs) (mapv second pairs)]
    [[] []]))