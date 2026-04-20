(defn fix_spaces
  "Given a string text, replace all spaces in it with underscores, 
  and if a string has more than 2 consecutive spaces, 
  then replace all consecutive spaces with -"
  [text]
  (clojure.string/replace text #"  +" "-"))