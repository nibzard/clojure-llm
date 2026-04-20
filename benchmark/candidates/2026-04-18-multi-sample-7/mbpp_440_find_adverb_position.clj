(defn find_adverb_position
  "	Write a function to find the first adverb and their positions in a given sentence."
  [text]
  (let [s (or text "")
        matcher (re-matcher #"\b\w+ly\b" s)]
    (when (.find matcher)
      (let [word (.group matcher)
            start (.start matcher)
            end (dec (.end matcher))]
        {:adverb word
         :start start
         :end end}))))