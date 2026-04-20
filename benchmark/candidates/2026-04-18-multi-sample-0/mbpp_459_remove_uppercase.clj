(defn remove_uppercase
  "	Write a function to remove uppercase substrings from a given string."
  [str1]
  (if (nil? str1)
    nil
    (clojure.string/replace str1 #"[A-Z]+" "")))