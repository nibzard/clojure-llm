(defn remove_uppercase
  "	Write a function to remove uppercase substrings from a given string."
  [str1]
  (clojure.string/trim
   (clojure.string/replace str1 #"\b[A-Z]+\b" "")))