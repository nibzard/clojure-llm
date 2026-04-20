(defn sum-in-range
  "Return the sum of all odd numbers in the inclusive range from `l` to `r`.

  Accepts `nil` bounds by treating them as unbounded:
  - `l` nil means start from negative infinity conceptually
  - `r` nil means go to positive infinity conceptually

  Examples:
  (sum-in-range 1 7)   ;=> 16
  (sum-in-range nil 5)  ;=> 9
  (sum-in-range 4 nil)  ;=> 5"
  [l r])