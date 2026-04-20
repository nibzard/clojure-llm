(defn is_happy?
  "You are given a collection xs.
  Return true if xs has length at least 3 and every 3 consecutive elements are distinct.
  The collection may be a list, vector, or lazy sequence.

  Examples:
  >>> (is_happy? [1 2 3])
  true
  >>> (is_happy? [1 1 2])
  false
  >>> (is_happy? '(a b c d))
  true
  >>> (is_happy? (range 5))
  true
  >>> (is_happy? [1 2])
  false"
  [xs])