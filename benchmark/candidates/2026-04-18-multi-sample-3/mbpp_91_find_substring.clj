(defn find_substring
  "Write a function to check if a string is present as a substring in a given list of string values."
  [str1 sub_str]
  (boolean
   (and (sequential? str1)
        (string? sub_str)
        (some #(and (string? %) (not= -1 (.indexOf ^String % ^String sub_str))) str1))))