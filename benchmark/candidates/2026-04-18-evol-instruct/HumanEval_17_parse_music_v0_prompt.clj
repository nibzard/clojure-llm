(defn parse_music
  "Given a string of ASCII drum hits, return a vector of durations in beats.
   Use this legend:
   'x'  - whole hit, lasts 4 beats
   'x|' - half hit, lasts 2 beats
   '.|' - quarter hit, lasts 1 beat

   Ignore extra whitespace, and return an empty vector for nil or blank input.

   Examples:
   (parse_music \"x x| .| x|\")
   => [4 2 1 2]

   (parse_music nil)
   => []"
  [music-string])