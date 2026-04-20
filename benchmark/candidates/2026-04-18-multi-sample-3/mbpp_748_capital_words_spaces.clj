(defn capital_words_spaces
  "	Write a function to put spaces between words starting with capital letters in a given string."
  [str1]
  (let [s (or str1 "")]
    (clojure.string/replace s #"(?<=[a-z])(?=[A-Z])" " ")))