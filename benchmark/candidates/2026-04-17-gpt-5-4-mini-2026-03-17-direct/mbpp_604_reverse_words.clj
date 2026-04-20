(defn reverse_words
  "	Write a function to reverse words seperated by spaces in a given string."
  [s]
  (when (some? s)
    (->> (clojure.string/split s #"\s+")
         reverse
         (clojure.string/join " "))))