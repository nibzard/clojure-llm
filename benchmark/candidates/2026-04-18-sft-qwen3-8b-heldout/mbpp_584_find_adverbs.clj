(defn find_adverbs
  "	Write a function to find the first adverb ending with ly and its positions in a given string."
  [text]
  (let [s (or text "")]
    (first
      (for [i (range 0 (count s))
            :let [start i
                  end (inc i)]
            :when (and (<= start end)
                       (<= end (count s))
                       (re-find #"\b\w+ly\b" (subs s start end)))
            :let [match (re-find #"\b(\w+ly)\b" (subs s start end))]
            :when (seq match)]
        {:adverb (second match)
         :start  start
         :end    end}))))