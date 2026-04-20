(defn replace_spaces
  "	Write a function to replace all spaces in the given string with '%20'."
  [string]
  (if (nil? string)
    nil
    (clojure.string/replace string " " "%20")))