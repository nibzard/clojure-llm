(defn replace_char
  "Write a function to replace characters in a string."
  [str1 ch newch]
  (when str1
    (clojure.string/replace str1 ch newch)))