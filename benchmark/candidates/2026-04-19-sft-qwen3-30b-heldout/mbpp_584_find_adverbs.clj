(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (when (seq text)
    (let [pattern #"\b\w+ly\b"
          matches (re-seq pattern text)]
      (when-let [match (first matches)]
        {:adverb (first match)
         :start  (first (re-find pattern text))
         :end    (second (re-find pattern text))}))))