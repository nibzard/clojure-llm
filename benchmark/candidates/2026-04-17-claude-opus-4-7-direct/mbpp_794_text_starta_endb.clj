(defn text_starta_endb
  "Write a function that matches a string that has an 'a' followed by anything, ending in 'b'."
  [text]
  (and (string? text)
       (re-matches #"^a.*b$" text)))