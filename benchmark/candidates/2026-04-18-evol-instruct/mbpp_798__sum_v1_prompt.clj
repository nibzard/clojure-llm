(defn sum-even-nums
  "Return the sum of the even numbers in a collection.

  Works with any sequential collection, ignores nil values, and returns 0 for an empty collection.

  Examples:
  (sum-even-nums [1 2 3 4]) => 6
  (sum-even-nums '(nil 6 7 8)) => 14
  (sum-even-nums []) => 0"
  [xs])