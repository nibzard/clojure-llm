(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (when (some? text)
    (let [matcher (re-matcher #"\b\w+ly\b" text)]
      (when (.find matcher)
        (let [adverb (.group matcher)
              start  (.start matcher)
              end    (.end matcher)]
          [adverb start end])))))