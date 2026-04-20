(defn kth_even
  "Return the kth even number from a possibly lazy sequence using 1-based indexing.

  Ignore nil values and odd numbers.
  Return nil if there are fewer than k even numbers.

  Examples:
  (kth_even [1 4 7 8 10] 2) => 8
  (kth_even '(nil 2 3 6 9 12) 3) => 12
  (kth_even (range) 5) => 8"
  [xs k])