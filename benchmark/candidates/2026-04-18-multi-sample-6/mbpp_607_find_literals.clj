(defn find_literals
  "	Write a function to search a string for a regex pattern. The function should return the matching subtring, a start index and an end index."
  [text pattern]
  (when (and (string? text) pattern)
    (let [re (if (instance? java.util.regex.Pattern pattern)
               pattern
               (re-pattern (str pattern)))
          matcher (re-matcher re text)]
      (when (.find matcher)
        [(.group matcher) (.start matcher) (.end matcher)]))))