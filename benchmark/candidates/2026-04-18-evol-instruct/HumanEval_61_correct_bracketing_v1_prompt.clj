(defn balanced-delimiters
  "Given a collection of delimiter characters, return true if it is properly balanced.
  Supports (), [], and {}. A nil input should return false.

  >>> (balanced-delimiters \"()[]{}\")
  true
  >>> (balanced-delimiters \"([{}])\")
  true
  >>> (balanced-delimiters \"([)]\")
  false
  >>> (balanced-delimiters nil)
  false"
  [delims])