(defn remove_all_spaces
  "	Write a function to remove all whitespaces from a string."
  [text]
  (if (nil? text)
    nil
    (clojure.string/replace text #"\s+" "")))