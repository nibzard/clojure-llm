(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (let [s (or text "")
        matcher (re-matcher #"\b\w+ly\b" s)]
    (when (.find matcher)
      (let [word (.group matcher)
            start (.start matcher)
            end (.end matcher)]
        [word start end]))))