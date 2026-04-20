(defn split-camel-words
  "Insert spaces before capital letters in a camelCase or PascalCase string.

Examples:
  (split-camel-words \"camelCaseWords\") => \"camel Case Words\"
  (split-camel-words \"XMLHttpRequest\") => \"XML Http Request\"

Return nil when input is nil."
  [s]
  (when s
    (clojure.string/replace s #"(?<=[a-z])(?=[A-Z])" " ")))