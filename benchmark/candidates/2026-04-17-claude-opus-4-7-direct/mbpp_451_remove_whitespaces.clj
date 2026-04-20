(defn remove-whitespaces
  "Write a function to remove all whitespaces from the given string."
  [text1]
  (clojure.string/replace text1 #"\s+" ""))