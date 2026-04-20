(defn remove_lowercase
  "	Write a function to remove lowercase substrings from a given string."
  [str1]
  (if (nil? str1)
    nil
    (clojure.string/replace str1 #"[a-z]+" "")))