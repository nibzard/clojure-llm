(defn interleave-prefix
  "Takes a collection of collections and returns a vector where each output
  element is the prefix of the corresponding input collection, up to that point.

  The function must work with lazy and/or infinite sequences.

  Examples:
  >>> (interleave-prefix [[1 2 3] [4 5]])
  [[1] [1 2] [1 2 3] [4] [4 5]]

  >>> (take 5 (interleave-prefix [(range) [:a :b :c]]))
  [[0] [0 1] [0 1 2] [0 1 2 3] [0 1 2 3 4]]"
  [colls])