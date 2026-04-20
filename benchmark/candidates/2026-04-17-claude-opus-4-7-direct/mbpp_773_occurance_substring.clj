(defn occurance_substring
  "Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
  [text pattern]
  (when (and text pattern)
    (loop [occurrences []
           idx 0]
      (let [found-idx (.indexOf text pattern idx)]
        (if (== -1 found-idx)
          (when (seq occurrences) occurrences)
          (recur (conj occurrences found-idx) (inc found-idx)))))))