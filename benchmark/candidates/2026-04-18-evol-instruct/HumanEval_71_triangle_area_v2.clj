(defn median-of-three
  "Given three values a, b, and c, return their median value.
  The median is the value that would be in the middle if the three values
  were sorted.

  Examples:
  >>> (median-of-three 3 1 2)
  2
  >>> (median-of-three 10 10 5)
  10
  >>> (median-of-three -1 7 3)
  3"
  [a b c]
  (nth (sort [a b c]) 1))