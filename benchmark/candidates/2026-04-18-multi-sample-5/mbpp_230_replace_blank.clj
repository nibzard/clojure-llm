(defn replace_blank
  "	Write a function that takes in a string and character, replaces blank spaces in the string with the character, and returns the string."
  [str1 char]
  (let [replacement (str char)]
    (if (nil? str1)
      nil
      (clojure.string/replace str1 " " replacement))))