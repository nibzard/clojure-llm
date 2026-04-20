(defn fix_spaces
  "Given a string text, replace all spaces in it with underscores, 
  and if a string has more than 2 consecutive spaces, 
  then replace all consecutive spaces with - "
  [text]
  (if (nil? text)
    nil
    (let [replaced-hyphens (clojure.string/replace text #" {3,}" "-")]
      (clojure.string/replace replaced-hyphens #" " "_"))))