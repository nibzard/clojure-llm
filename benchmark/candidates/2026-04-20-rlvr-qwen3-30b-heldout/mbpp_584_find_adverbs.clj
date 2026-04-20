(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (when (string? text)
    (let [matches (re-seq #"\b\w+ly\b" text)]
      (when-let [m (first matches)]
        {:word (first m)
         :start (-> text (.indexOf (first m)))
         :end (+ (-> text (.indexOf (first m))) (count (first m)))}))))