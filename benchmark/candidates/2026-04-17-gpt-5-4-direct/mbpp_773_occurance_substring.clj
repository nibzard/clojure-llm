(defn occurance_substring
  "Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
  [text pattern]
  (when (and (string? text) (string? pattern) (not (empty? pattern)))
    (let [plen (count pattern)
          tlen (count text)
          positions (->> (range 0 (inc (- tlen plen)))
                         (filter #(= pattern (subs text % (+ % plen))))
                         vec)]
      (when (seq positions)
        {:occurrence (count positions)
         :position positions}))))