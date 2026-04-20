(defn occurance_substring
  "Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
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
          (let [found (.indexOf text pattern idx)]
            (if (= found -1)
              (when (seq positions)
                {:occurrence (count positions)
                 :position positions})
              (recur (inc found) (conj positions found))))))))