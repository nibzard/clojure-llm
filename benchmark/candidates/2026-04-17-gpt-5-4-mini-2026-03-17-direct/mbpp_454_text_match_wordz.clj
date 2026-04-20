(defn text_match_wordz
  "Write a function that matches a word containing 'z'."
  [text]
  (boolean (re-find #"\b\w*z\w*\b" (or text ""))))