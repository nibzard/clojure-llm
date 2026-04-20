(defn find_literals
  "Write a function to search a string for a regex pattern. The function should return the matching subtring, a start index and an end index."
  [text pattern]
  (when (and text pattern)
    (let [m (re-matcher (re-pattern pattern) text)]
      (when (re-find m)
        [(.group m) (.start m) (.end m)]))))