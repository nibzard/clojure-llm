(defn running-average
  "Given a possibly lazy sequence of numbers, return a lazy sequence of the
  running average after each element.

  The result should be a sequence where the nth item is the average of the
  first n+1 input values.

  Examples:
  >>> (running-average [1 2 3 4])
  (1.0 1.5 2.0 2.5)

  >>> (take 3 (running-average (range)))
  (0.0 0.5 1.0)"
  [numbers])