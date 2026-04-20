(defn double_the_difference
  "Given a list of numbers, return the sum of squares of the numbers
  in the list that are odd. Ignore numbers that are negative or not integers.
  >>> (double_the_difference [1 3 2 0])
  10
  >>> (double_the_difference [-1 -2 0])
  0
  >>> (double_the_difference [9 -2])
  81
  >>> (double_the_difference [0])
  0
  If the input list is empty, return 0."
  [lst]
  (reduce
    (fn [sum n]
      (if (and (integer? n) (pos? n) (odd? n))
        (+ sum (* n n))
        sum))
    0
    lst))