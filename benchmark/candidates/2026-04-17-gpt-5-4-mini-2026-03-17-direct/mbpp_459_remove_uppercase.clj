(defn remove_uppercase
  "	Write a function to remove uppercase substrings from a given string."
  [str1]
  (if (string? str1)
    (clojure.string/replace str1 #"[A-Z]+" "")
    str1))