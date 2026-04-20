(defn text_match_wordz
  "Return the first word in `text` that contains the letter z or Z.

  Examples:
  (text_match_wordz \"lazy dog\") => \"lazy\"
  (text_match_wordz \"No match here\") => nil
  (text_match_wordz nil) => nil"
  [text])