(defn replace_spaces
  "	Write a function to replace all spaces in the given string with '%20'."
  [string]
  (when string
    (apply str (map #(if (= % \space) "%20" (str %)) string))))