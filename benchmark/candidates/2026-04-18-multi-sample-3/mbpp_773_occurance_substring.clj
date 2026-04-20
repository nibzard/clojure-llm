(defn occurance_substring
  "	Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
  [text pattern]
  (when (and (string? text) (string? pattern) (not (empty? pattern)))
    (let [plen (.length pattern)
          tlen (.length text)]
      (loop [idx 0
             positions []]
        (if (> idx (- tlen plen))
          (when (seq positions)
            {:occurrence (count positions)
             :position positions})
          (if (= pattern (subs text idx (+ idx plen)))
            (recur (inc idx) (conj positions idx))
            (recur (inc idx) positions)))))))