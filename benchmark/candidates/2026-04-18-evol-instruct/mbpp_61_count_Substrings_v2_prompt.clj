(defn count-even-prefix-sums
  "Return the number of prefixes of coll whose sum is even.

  Works with any sequence of integers, including lazy and infinite sequences
  when enough elements are present to determine the result.

  Examples:
  (count-even-prefix-sums [1 2 3 4]) ;=> 2
  (count-even-prefix-sums [2 2 2])   ;=> 3
  (count-even-prefix-sums nil)       ;=> 0"
  [coll])