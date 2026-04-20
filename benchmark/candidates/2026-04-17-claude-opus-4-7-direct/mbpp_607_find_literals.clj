(defn find_literals
  "Write a function to search a string for a regex pattern. The function should return the matching subtring, a start index and an end index."
  [text pattern]
  (let [matcher (re-matcher pattern text)]
    (when (.find matcher)
      [(.group matcher) (.start matcher) (.end matcher)])))