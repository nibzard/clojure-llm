(defn find_substring
  "	Write a function to check if a string is present as a substring in a given list of string values."
  [str1 sub_str]
  (boolean
   (some #(and (string? %) (string? sub_str) (not (nil? (.indexOf ^String % ^String sub_str)))
               (>= (.indexOf ^String % ^String sub_str) 0))
         str1)))