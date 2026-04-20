(defn find_substring
  "	Write a function to check if a string is present as a substring in a given list of string values."
  [str1 sub_str]
  (boolean
   (some #(and (string? %) (string? sub_str) (.contains ^String % sub_str))
         str1)))