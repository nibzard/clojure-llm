(defn replace_spaces
  "	Write a function to replace all spaces in the given string with '%20'."
  [string]
  (clojure.string/replace string " " "%20"))