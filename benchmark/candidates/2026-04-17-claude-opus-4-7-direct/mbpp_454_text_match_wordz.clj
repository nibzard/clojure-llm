(defn text_match_wordz
  "Write a function that matches a word containing 'z'."
  [text]
  (and (string? text)
       (re-find #"\w*z\w*" text)))