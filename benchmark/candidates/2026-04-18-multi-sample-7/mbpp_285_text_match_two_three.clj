(defn text_match_two_three
  "Write a function that checks whether a string contains the 'a' character followed by two or three 'b' characters."
  [text]
  (boolean
   (and (string? text)
        (re-find #"ab{2,3}" text))))