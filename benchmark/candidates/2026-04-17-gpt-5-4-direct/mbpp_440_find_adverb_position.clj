(defn find_adverb_position
  "	Write a function to find the first adverb and their positions in a given sentence."
  [text]
  (let [s (or text "")
        matcher (re-matcher #"\b\w+ly\b" s)]
    (when (.find matcher)
      {:adverb (.group matcher)
       :position [(.start matcher) (.end matcher)]})))