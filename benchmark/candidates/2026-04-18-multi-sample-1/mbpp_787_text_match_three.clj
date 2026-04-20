(defn text_match_three
  "	Write a function that matches a string that has an a followed by three 'b'."
  [text]
  (boolean
   (and (string? text)
        (re-find #"abbb" text))))