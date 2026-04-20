(defn find_literals
  "Write a function to search a string for a regex pattern. The function should return the matching substring, a start index and an end index."
  [text pattern]
  (when-let [m (re-find pattern text)]
    (let [match (if (vector? m) (first m) m)
          idx (.indexOf ^String text ^String match)]
      [match idx (+ idx (count match))])))