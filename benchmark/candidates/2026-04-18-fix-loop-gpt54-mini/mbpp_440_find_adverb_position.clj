(defn find_adverb_position
  "	Write a function to find the first adverb and their positions in a given sentence."
  [text]
  (let [matcher (re-matcher #"\b\w+ly\b" text)]
    (when (.find matcher)
      [(.group matcher) (.start matcher)])))