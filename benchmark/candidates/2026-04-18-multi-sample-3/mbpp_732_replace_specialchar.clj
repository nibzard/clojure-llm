(defn replace_specialchar
  "	Write a function to replace all occurrences of spaces, commas, or dots with a colon."
  [text]
  (when (some? text)
    (clojure.string/replace text #"[ ,.]" ":")))