(defn sort_by_ends-parity
  "Given a vector of integers, return a new vector sorted based on the parity of the sum of
  the first and last elements.

  If the sum of the first and last elements is odd, sort the vector in ascending order.
  If the sum is even, sort the vector in descending order.

  Requirements:
  - Do not mutate the input vector.
  - Return an empty vector unchanged.
  - Work correctly for vectors containing negative numbers and duplicates.

  Examples:
  >>> (sort_by_ends-parity [])
  []
  >>> (sort_by_ends-parity [5])
  [5]
  >>> (sort_by_ends-parity [2 -4 3 0 1 5])
  [5 3 2 1 0 -4]
  >>> (sort_by_ends-parity [2 4 3 0 1 6])
  [6 4 3 2 1 0]"
  [v])