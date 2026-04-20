(defn replace_spaces
  "	Write a function to replace all spaces in the given string with '%20'."
  [string]
  (when (some? string)
    (apply str (replace {\space "%20"} string))))