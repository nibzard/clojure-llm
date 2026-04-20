(defn remove_lowercase
  "	Write a function to remove lowercase substrings from a given string."
  [str1]
  (if (string? str1)
    (clojure.string/replace str1 #"[a-z]+" "")
    str1))