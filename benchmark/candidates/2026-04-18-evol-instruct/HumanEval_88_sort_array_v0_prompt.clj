(defn rotate-sort
  "Given a vector of integers, return a new vector sorted in ascending order when the
  sum of the first and last elements is even, or sorted in descending order when that
  sum is odd.

  If the input vector is empty or has one element, return it unchanged.

  Do not modify the original vector.

  Examples:
  >>> (rotate-sort [])
  []
  >>> (rotate-sort [5])
  [5]
  >>> (rotate-sort [2 4 3 0 1 5])
  [5 4 3 2 1 0]
  >>> (rotate-sort [2 4 3 0 1 6])
  [0 1 2 3 4 6]"
  [v])