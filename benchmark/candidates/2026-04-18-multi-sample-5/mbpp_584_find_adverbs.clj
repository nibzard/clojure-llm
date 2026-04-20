(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (let [s (or text "")
        m (re-find #"\b\w+ly\b" s)]
    (when m
      (let [idx (.indexOf s m)]
        [m idx (+ idx (count m))]))))