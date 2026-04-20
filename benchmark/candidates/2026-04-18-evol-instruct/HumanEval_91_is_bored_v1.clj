(defn count-starting-is
  "Count how many sentences in a collection start with the word \"I\".

  A sentence is represented as a string in a vector of sentences.
  Return the number of sentences whose first non-whitespace character is the
  word \"I\" followed by a word boundary.

  Examples:
  >>> (count-starting-is [\"Hello world\" \"I like Clojure\" \"  I am here\" \"This is fine\"])
  2
  >>> (count-starting-is [])
  0
  >>> (count-starting-is [nil \"\" \"  !\" \"I.\"])
  1"
  [sentences]
  (count (filter #(and (string? %)
                       (re-find #"^\s*I\b" %))
                 sentences)))