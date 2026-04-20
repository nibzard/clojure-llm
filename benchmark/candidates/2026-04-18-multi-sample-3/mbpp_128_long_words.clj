(defn long_words
  "	Write a function to find words that are longer than n characters from a given list of words."
  [n str]
  (let [words (cond
                (nil? str) []
                (string? str) (clojure.string/split str #"\s+")
                (sequential? str) str
                :else [])]
    (filter #(> (count %) n) words)))