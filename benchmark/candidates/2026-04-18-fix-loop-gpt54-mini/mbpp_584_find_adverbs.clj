(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (let [m (re-find #"\b\w+ly\b" text)]
    (when m
      (let [idx (.indexOf ^String text ^String m)]
        [m idx (+ idx (count m))]))))