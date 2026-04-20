(defn find_literals
  "	Write a function to search a string for a regex pattern. The function should return the matching subtring, a start index and an end index."
  [text pattern]
  (when (and (some? text) (some? pattern))
    (let [re (if (instance? java.util.regex.Pattern pattern)
               pattern
               (re-pattern pattern))
          m  (re-matcher re text)]
      (when (.find m)
        [(.group m) (.start m) (.end m)]))))