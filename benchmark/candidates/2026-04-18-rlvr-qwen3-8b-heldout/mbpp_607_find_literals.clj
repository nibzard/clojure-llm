(defn find_literals
  "	Write a function to search a string for a regex pattern. The function should return the matching subtring, a start index and an end index."
  [text pattern]
  (let [m (when (and text pattern)
            (re-find (java.util.regex.Pattern/compile pattern) text))]
    (when m
      [(first m) 0 (dec (count text))])))