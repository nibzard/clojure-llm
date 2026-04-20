(defn occurance_substring
  "	Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
  [text pattern]
  (when (and (string? text) (string? pattern) (not (empty? pattern)))
    (let [matches (keep-indexed
                   (fn [idx _]
                     (when (and (<= (+ idx (count pattern)) (count text))
                                (= pattern (subs text idx (+ idx (count pattern)))))
                       [idx pattern]))
                   text)]
      (seq matches))))