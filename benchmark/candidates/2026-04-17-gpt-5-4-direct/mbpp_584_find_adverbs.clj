(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (when (some? text)
    (let [m (re-find #"\b\w+ly\b" text)]
      (when m
        (let [start (.indexOf ^String text ^String m)
              end (+ start (count m))]
          [m start end])))))