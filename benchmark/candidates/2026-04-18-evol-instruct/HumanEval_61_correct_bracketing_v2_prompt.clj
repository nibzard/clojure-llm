(defn balanced-delimiters
  "delims is a string containing only the characters (), [], and {}.
  Return true if the delimiters are properly balanced and nested.

  >>> (balanced-delimiters \"()\")
  true
  >>> (balanced-delimiters \"([{}])\")
  true
  >>> (balanced-delimiters \"([)]\")
  false
  >>> (balanced-delimiters \"(([\") 
  false"
  [delims])