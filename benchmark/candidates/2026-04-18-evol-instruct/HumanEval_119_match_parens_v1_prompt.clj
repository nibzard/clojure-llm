(defn balanced-from-parts
  "Given a vector of two strings containing only '(' and ')', determine whether
  there exists an order of concatenation that makes a balanced parentheses
  string.

  Return \"Yes\" if either a+b or b+a is balanced, otherwise return \"No\".

  Examples:
  >>> (balanced-from-parts [\"()\" \"(())\"])
  \"Yes\"
  >>> (balanced-from-parts [\")\" \"(\"])
  \"Yes\"
  >>> (balanced-from-parts [\"(\" \")(\"])
  \"No\""
  [parts])