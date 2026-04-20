(defn find_closest_infinite_pair
  "From a supplied sequence of numbers (which may be infinite), return the first adjacent pair with the smallest absolute difference.
  The result should be a vector of the two numbers in their original order.
  If multiple adjacent pairs tie for the smallest difference, return the earliest such pair.

  Examples:
  >>> (find_closest_infinite_pair [1 4 7 10])
  [1 4]
  >>> (find_closest_infinite_pair [10 3 8 6])
  [8 6]
  >>> (find_closest_infinite_pair (iterate inc 0))
  [0 1]"
  [numbers])