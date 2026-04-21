(defn find_substring
  "Write a function to check if a string is present as a substring in a given list of string values."
  [str1 sub_str]
  (boolean (some #(clojure.string/includes? % sub_str) str1)))