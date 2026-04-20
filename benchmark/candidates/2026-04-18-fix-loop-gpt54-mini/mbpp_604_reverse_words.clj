(defn reverse_words
  "	Write a function to reverse words seperated by spaces in a given string."
  [s]
  (clojure.string/join " " (reverse (clojure.string/split s #" "))))