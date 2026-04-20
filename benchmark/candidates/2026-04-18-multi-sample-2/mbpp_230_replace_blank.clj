(defn replace_blank
  "	Write a function that takes in a string and character, replaces blank spaces in the string with the character, and returns the string."
  [str1 char]
  (let [s (or str1 "")
        replacement (str char)]
    (apply str (map #(if (= % \space) (first replacement) %) s))))