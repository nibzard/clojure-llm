(defn text_match_wordz
  "Return a lazy sequence of words from the input text that contain the letter \"z\" or \"Z\".

  Examples:
  (text_match_wordz \"zebra fizz buzz\") => (\"zebra\" \"fizz\" \"buzz\")
  (text_match_wordz nil) => ()"
  [text])