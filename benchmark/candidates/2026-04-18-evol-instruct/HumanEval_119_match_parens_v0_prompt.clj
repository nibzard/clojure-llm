(defn longest-balanced-prefix
  "Given a string containing only '(' and ')', return the length of the longest
  prefix that is a balanced parentheses string.

  A prefix is any initial substring of the original string. A prefix is balanced
  if it never has more ')' than '(' at any point, and ends with equal numbers of
  '(' and ')'.

  Return 0 if no balanced prefix exists.

  Examples:
  >>> (longest-balanced-prefix \"()())\")
  4
  >>> (longest-balanced-prefix \"((()))xyz\")
  6
  >>> (longest-balanced-prefix \"(\")
  0"
  [s])