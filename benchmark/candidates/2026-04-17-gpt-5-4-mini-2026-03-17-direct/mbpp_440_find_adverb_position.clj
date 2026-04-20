(defn find_adverb_position
  "Write a function to find the first adverb and their positions in a given sentence."
  [text]
  (let [words (when (string? text)
                (re-seq #"\b[\w'-]+\b" text))
        adverb? #(re-find #"(?i)ly$" %)]
    (when-let [idx (some (fn [[i w]]
                           (when (adverb? w) i))
                         (map-indexed vector words))]
      [idx (nth words idx)])))