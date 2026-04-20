(defn merge-valid-parens
  "You are given a vector of two strings, each string contains only open
  parentheses '(' and close parentheses ')' characters.

  Determine whether it is possible to concatenate the two strings in either
  order so that the resulting string is a valid parentheses string.

  Return true if one of the two possible concatenations is valid, otherwise
  return false.

  Examples:
  >>> (merge-valid-parens [\"()(\" \")\"])
  true
  >>> (merge-valid-parens [\")\" \")\"])
  false
  >>> (merge-valid-parens [\"((\" \"))\"])
  true"
  [pairs])