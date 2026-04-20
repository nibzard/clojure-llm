(defn normalize-key-case
  "Given a map, return :lower if all string keys are lowercase, :upper if all string keys are uppercase, :mixed if string keys use both cases, and :invalid if any key is not a string. Return :empty for an empty map.

  Examples:
  >>> (normalize-key-case {\"a\" 1, \"b\" 2})
  :lower
  >>> (normalize-key-case {\"A\" 1, \"B\" 2})
  :upper
  >>> (normalize-key-case {\"a\" 1, \"B\" 2})
  :mixed
  >>> (normalize-key-case {\"a\" 1, 8 2})
  :invalid
  >>> (normalize-key-case {})
  :empty"
  [m])