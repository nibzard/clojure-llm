(defn normalize_keyword_keys
  "Given a map, return a new map where all keyword keys are converted to strings.

  - Keyword keys should be converted with `name`
  - Non-keyword keys must be left unchanged
  - If the input map is empty, return an empty map
  - The function should preserve all values

  Examples:
  >>> (normalize_keyword_keys {:a 1 :b 2})
  {\"a\" 1, \"b\" 2}
  >>> (normalize_keyword_keys {:a 1 \"b\" 2 3 4})
  {\"a\" 1, \"b\" 2, 3 4}
  >>> (normalize_keyword_keys {})
  {}"
  [m])