(defn text_match_wordz_middle
  "Write a function that checks if a strings contains 'z', except at the start and end of the word."
  [text]
  (boolean
   (some #(re-find #"(?i)\wz\w" %)
         (re-seq #"\S+" (or text "")))))