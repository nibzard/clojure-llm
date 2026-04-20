(defn parse_music
  "Input is a string containing a sequence of note tokens separated by arbitrary whitespace.
  Return a vector of beat lengths for each token.

  Token legend:
  \"w\"  - whole note, lasts 4 beats
  \"h\"  - half note, lasts 2 beats
  \"q\"  - quarter note, lasts 1 beat

  Ignore extra spaces, tabs, and newlines.

  Examples:
  (parse_music \"w h q\")
  => [4 2 1]

  (parse_music \"  q\\n\\th   w   q \")
  => [1 2 4 1]"
  [music-string])