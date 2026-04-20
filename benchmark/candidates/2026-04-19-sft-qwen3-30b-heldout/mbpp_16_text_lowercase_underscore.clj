(defn text_lowercase_underscore
  "	Write a function to that returns true if the input string contains sequences of lowercase letters joined with an underscore and false otherwise."
  [text]
  (boolean
   (and (string? text)
        (re-find #"\b[a-z]+_[a-z]+\b" text))))