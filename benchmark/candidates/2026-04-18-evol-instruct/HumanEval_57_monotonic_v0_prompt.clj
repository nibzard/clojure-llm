(defn monotonic-prefix
  "Return the longest monotonic prefix of a collection.
  A prefix is monotonic if it is entirely nondecreasing or entirely nonincreasing.

  Works with any sequential collection, including vectors and lists.

  >>> (monotonic-prefix [1 2 2 5 3 4])
  [1 2 2 5]
  >>> (monotonic-prefix '(9 7 7 2 3))
  (9 7 7 2)
  >>> (monotonic-prefix [5])
  [5]
  >>> (monotonic-prefix [])
  []"
  [coll])