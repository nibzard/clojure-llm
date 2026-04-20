(defn remove_lowercase
  "	Write a function to remove lowercase substrings from a given string."
  [str1]
  (if (nil? str1)
    nil
    (apply str (re-seq #"[A-Z]+" str1))))