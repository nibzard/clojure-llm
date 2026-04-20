(defn occurance_substring
  "	Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
  [text pattern]
  (when (and (string? text) (string? pattern) (not (empty? pattern)))
    (let [pattern-len (count pattern)
          positions (loop [start 0
                           acc []]
                      (let [idx (.indexOf ^String text ^String pattern start)]
                        (if (neg? idx)
                          acc
                          (recur (inc idx) (conj acc idx)))))]
      (when (seq positions)
        {:occurrence (count positions)
         :position positions}))))