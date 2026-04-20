(defn long_words
  "	Write a function to find words that are longer than n characters from a given list of words."
  [n str]
  (let [words (if (string? str) (clojure.string/split str #"\s+") [str])]
    (->> words
         (filter (fn [w] (> (count w) n)))
         (map str)
         (vec))))