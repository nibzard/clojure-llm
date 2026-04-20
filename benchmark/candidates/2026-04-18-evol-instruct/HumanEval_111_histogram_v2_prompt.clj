(defn histogram
  "Given a collection of integers, return a map of the values that occur most often
  and their corresponding counts.
  If several values have the same highest frequency, return all of them.
  Ignore nil values.
  Examples:
  >>> (histogram [1 2 2 3 3 3])
  {3 3}
  >>> (histogram [1 1 2 2 3])
  {1 2, 2 2}
  >>> (histogram [nil 4 4 nil 5])
  {4 2}
  >>> (histogram [])
  {}"
  [coll])