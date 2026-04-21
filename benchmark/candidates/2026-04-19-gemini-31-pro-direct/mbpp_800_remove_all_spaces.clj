(defn remove_all_spaces
  "Write a function to remove all whitespaces from a string."
  [text]
  (when text
    (clojure.string/replace text #"\s+" "")))