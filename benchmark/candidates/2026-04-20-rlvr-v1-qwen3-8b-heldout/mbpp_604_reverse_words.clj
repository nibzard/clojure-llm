(defn reverse_words
  "	Write a function to reverse words seperated by spaces in a given string."
  [s]
  (if (seq s)
    (let [words (re-seq #"\S+" s)]
      (apply str (interpose " " (reverse words))))
    s))