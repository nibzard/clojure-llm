(defn text_match_zero_one
  "Write a function that matches a string that has an 'a' followed by one or more 'b's."
  [text]
  (and (string? text)
       (boolean (re-matches #".*ab+.*" text))))