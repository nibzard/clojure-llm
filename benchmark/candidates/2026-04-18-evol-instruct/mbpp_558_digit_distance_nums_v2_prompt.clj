(defn digit-distance-coll
  "Return the sum of absolute differences between corresponding digits in two collections of digits.

  The collections may be vectors or sequences of integers. If one collection is shorter,
  treat missing digits as 0.

  Examples:
  (digit-distance-coll [1 2 3] [4 5 6]) ;=> 9
  (digit-distance-coll [9 1] [2 8 7])   ;=> 11
  (digit-distance-coll nil [1 2 3])     ;=> 6"
  [xs ys])