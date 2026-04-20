(defn count_up_to
  "Return a vector of all prime numbers less than n.

  This variation must treat nil as 0, and it must work correctly for negative
  inputs by returning an empty vector.

  Examples:
  >>> (count_up_to nil)
  []
  >>> (count_up_to -5)
  []
  >>> (count_up_to 10)
  [2 3 5 7]
  >>> (count_up_to 2)
  []
  >>> (count_up_to 20)
  [2 3 5 7 11 13 17 19]"
  [n])