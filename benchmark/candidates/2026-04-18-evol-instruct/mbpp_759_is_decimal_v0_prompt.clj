(defn is_decimal_in_range
  "Return true if x is a decimal number with exactly 2 digits after the decimal point
   and its numeric value is within the inclusive range [0.00, 100.00].

   Examples:
   (is_decimal_in_range \"12.34\") => true
   (is_decimal_in_range \"0.00\")  => true
   (is_decimal_in_range \"100.01\") => false
   (is_decimal_in_range nil)       => false"
  [x])