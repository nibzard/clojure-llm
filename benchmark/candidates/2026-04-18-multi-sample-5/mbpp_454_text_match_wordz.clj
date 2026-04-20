(defn text_match_wordz
  "	Write a function that matches a word containing 'z'."
  [text]
  (boolean
   (and text
        (re-find #"\b\w*z\w*\b" text))))