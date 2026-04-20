(defn max-prefix-sum
  "Given a possibly empty collection of integers, return the largest sum of any prefix.
  A prefix may be the whole collection, so this works well with infinite sequences too.

  Examples:
  (max-prefix-sum [1 2 3]) => 6
  (max-prefix-sum [-5 -2 -8]) => -5
  (max-prefix-sum []) => nil
  (max-prefix-sum (take 4 (repeat 2))) => 8"
  [xs])