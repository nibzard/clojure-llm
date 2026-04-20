(defn find_adverbs
  "Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (when (string? text)
    (let [m (re-matcher #"\b\w+ly\b" text)]
      (when (.find m)
        [(.group m) (.start m) (.end m)]))))