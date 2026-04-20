(defn find_adverbs
  "Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (when (and text (pos? (count text)))
    (let [words (re-seq #"\w+" text)
          word-idxs (re-seq #"\w+" text)
          ;; Create a map of word -> list of indices (0-based)
          idx-map (reduce (fn [m [w i]]
                            (update m i conj w))
                          {}
                          (map vector words (range)))
          ;; Find the first word ending in "ly"
          adverb (first (filter #(re-find #"ly$" %) words))]
      (when adverb
        ;; Find all indices where this adverb appears
        (let [positions (keep-indexed #(when (= %2 adverb) %1) words)]
          {:word adverb
           :index (first positions)
           :positions positions})))))