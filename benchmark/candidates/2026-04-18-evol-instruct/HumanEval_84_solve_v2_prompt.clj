(defn solve
  "Given a collection of integers, return a map of the count of each digit in their decimal representations.
  Ignore signs for negative numbers, and treat nil as an empty collection.

  Example:
  >>> (solve [10 202 nil -33])
  {\\1 1, \\0 3, \\2 2, \\3 2}

  Example:
  >>> (solve [])
  {}

  Variables:
      @xs collection of integers or nil
           Constraints: may be empty, may contain negative numbers and nil values.
  Output:
      a map from digit characters to counts"
  [xs])