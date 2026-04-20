(defn change_base
  "Change numerical base of input number x to base.
  return string representation after the conversion.
  base numbers are less than 10."
  [x base]
  (Long/toString (long x) base))