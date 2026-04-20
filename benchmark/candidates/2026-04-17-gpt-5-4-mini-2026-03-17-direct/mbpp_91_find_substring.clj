(defn find_substring
  "	Write a function to check if a string is present as a substring in a given list of string values."
  [str1 sub_str]
  (boolean (and (string? str1)
                (string? sub_str)
                (not (nil? (clojure.string/index-of str1 sub_str))))))