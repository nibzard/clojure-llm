(defn rotate-right
  "Return the 32-bit unsigned integer obtained by rotating n right by d bits.
  Treat n as a 32-bit value and normalize d so it may be larger than 32.

  Examples:
  (rotate-right 13 2) => 3221225475
  (rotate-right 1 1)  => 2147483648
  (rotate-right -1 5) => 4294967295"
  [n d])