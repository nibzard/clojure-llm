(defn max-number-from-digits
  "Return the largest non-negative integer that can be formed by concatenating the
  digits in `digits`.

  Accepts any sequential collection of integers. Nil and empty collections return 0.

  Examples:
  (max-number-from-digits [3 30 34 5 9])  ;=> 9534330
  (max-number-from-digits [0 0 1])        ;=> 100
  (max-number-from-digits nil)            ;=> 0"
  [digits])