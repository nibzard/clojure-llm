(defn reverse_words
  "	Write a function to reverse words seperated by spaces in a given string."
  [s]
  (if (nil? s)
    nil
    (->> (clojure.string/split s #" ")
         reverse
         (clojure.string/join " "))))