(defn remove_lowercase
  "Write a function to remove lowercase substrings from a given string."
  [str1]
  (apply str (remove #(Character/isLowerCase %) str1)))