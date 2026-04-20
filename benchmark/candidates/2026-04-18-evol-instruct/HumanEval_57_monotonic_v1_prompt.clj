(defn monotonic-slices
  "Return true if the vector can be split into contiguous slices of length 2 or more,
  where each slice is strictly monotonic (either increasing or decreasing), and the
  direction alternates between adjacent slices.

  Empty vector and single-element vector are considered true.

  >>> (monotonic-slices [])
  true
  >>> (monotonic-slices [1])
  true
  >>> (monotonic-slices [1 2 3 2 1])
  true
  >>> (monotonic-slices [1 3 5 4 2 6 7])
  true
  >>> (monotonic-slices [1 2 3 3 2])
  false"
  [v])