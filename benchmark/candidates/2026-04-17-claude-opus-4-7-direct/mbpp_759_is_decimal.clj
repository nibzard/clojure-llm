(defn is_decimal
  "Write a function to check whether a given string is a decimal number with a precision of 2."
  [num]
  (and (string? num)
       (re-matches #"-?\d+\.\d{2}" num)))