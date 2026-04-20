(defn is_bored
  "You'll be given a string of words, and your task is to count the number
  of boredoms. A boredom is a sentence that starts with the word \"I\".
  Sentences are delimited by '.', '?' or '!'."
  [S]
  (if (str/blank? S)
    0
    (->> (str/split S #"[.?!]")
         (map str/trim)
         (filter #(re-matches #"^I\s.*" %))
         count)))