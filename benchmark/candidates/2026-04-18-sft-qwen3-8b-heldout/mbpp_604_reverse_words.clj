(defn reverse_words
  "	Write a function to reverse words seperated by spaces in a given string."
  [s]
  (if (seq s)
    (apply str (reverse (re-seq #"\S+" s)))
    s))