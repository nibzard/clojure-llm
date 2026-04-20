(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (when (some? text)
    (when-let [m (re-find #"\b\w+ly\b" text)]
      {:adverb m
       :start (.indexOf ^String text ^String m)
       :end (+ (.indexOf ^String text ^String m) (count m))})))