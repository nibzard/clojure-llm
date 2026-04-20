(defn replace_blank
  "Write a function that takes in a string and character, replaces blank spaces in the string with the character, and returns the string."
  [str1 char]
  (when (some? str1)
    (clojure.string/replace str1 #" " (str char))))