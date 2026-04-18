(defn long_words
  "	Write a function to find words that are longer than n characters from a given list of words."
  [n str]
  (let [words (or str [])]
    (->> words
         (filter #(and (string? %) (>= (count %) (inc n))))
         seq)))