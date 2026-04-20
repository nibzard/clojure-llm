(defn split-camel-words
  "Put spaces between word boundaries in a CamelCase or PascalCase string.

Examples:
  (split-camel-words \"HelloWorld\") => \"Hello World\"
  (split-camel-words \"XMLHttpRequest\") => \"XML Http Request\"
  (split-camel-words nil) => nil

Preserve existing spaces and return nil when given nil."
  [s])