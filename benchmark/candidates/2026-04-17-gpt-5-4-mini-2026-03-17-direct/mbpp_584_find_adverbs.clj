(defn find_adverbs
  "Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (when-let [m (re-find #"\b\w+ly\b" text)]
    {:adverb m
     :positions [(clojure.string/index-of text m)]}))