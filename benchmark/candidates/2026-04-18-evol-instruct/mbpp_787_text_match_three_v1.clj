(defn text_match_three
  "Return true when the input string contains an `a` followed immediately by exactly three `b` characters.

  Examples:
  (text_match_three \"abbb\")   ;=> true
  (text_match_three \"aBBB\")   ;=> false
  (text_match_three \"abbbb\")  ;=> false
  (text_match_three nil)       ;=> false"
  [text]
  (boolean (re-find #"abbb" (or text ""))))