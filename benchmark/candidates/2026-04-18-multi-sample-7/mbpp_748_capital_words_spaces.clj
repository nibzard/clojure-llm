(defn capital_words_spaces
  "	Write a function to put spaces between words starting with capital letters in a given string."
  [str1]
  (if (nil? str1)
    nil
    (clojure.string/replace str1 #"(?<!^)([A-Z])" " $1")))