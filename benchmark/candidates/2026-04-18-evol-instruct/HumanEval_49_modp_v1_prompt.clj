(defn modp
  "Return 2^n modulo p for n given as a potentially large string.
  Use only the decimal digits of n; ignore any leading/trailing whitespace.
  Return nil if n is not a valid non-negative integer string or if p is not positive.

  >>> (modp \"3\" 5)
  3
  >>> (modp \" 1101 \" 101)
  2
  >>> (modp \"0\" 101)
  1
  >>> (modp \"100\" 101)
  1
  >>> (modp \"12a\" 7)
  nil"
  [n p])