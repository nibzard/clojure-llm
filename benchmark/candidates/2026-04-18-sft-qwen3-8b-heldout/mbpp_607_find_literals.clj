(defn find_literals
  "	Write a function to search a string for a regex pattern. The function should return the matching subtring, a start index and an end index."
  [text pattern]
  (when-let [m (and text pattern (re-find pattern text))]
    [(second m) (.indexOf text (first m)) (.indexOf text (second m) 1)]))