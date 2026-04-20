(defn remove_whitespaces
  "	Write a function to remove all whitespaces from the given string."
  [text1]
  (if (nil? text1)
    nil
    (clojure.string/replace text1 #"\s+" "")))