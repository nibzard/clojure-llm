(defn find-adverb-position
  "Write a function to find the first adverb and their positions in a given sentence."
  [text]
  (when (and (string? text) (pos? (count text)))
    (let [words (clojure.string/split text #"\s+")
          adverb? #(and (string? %) (clojure.string/ends-with? % "ly"))]
      (loop [i 0
             w words]
        (when (seq w)
          (if (adverb? (first w))
            {:word (first w) :position i}
            (recur (inc i) (rest w))))))))