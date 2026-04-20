(defn replace_char
  "Write a function to replace characters in a string."
  [str1 ch newch]
  (clojure.string/replace str1 (re-pattern (str ch)) newch))