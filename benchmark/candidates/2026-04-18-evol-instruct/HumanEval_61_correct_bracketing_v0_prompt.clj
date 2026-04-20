(defn balanced-delimiters
  "delimiters is a string containing any of the characters (), [], and {}.
  Return true if the delimiters are properly balanced and nested, false otherwise.
  Non-delimiter characters should be ignored.

  >>> (balanced-delimiters \"(a[1]{b})\")
  true
  >>> (balanced-delimiters \"([)]\")
  false
  >>> (balanced-delimiters \"foo(bar)[baz]{qux}\")
  true
  >>> (balanced-delimiters \"(\")
  false"
  [delimiters])