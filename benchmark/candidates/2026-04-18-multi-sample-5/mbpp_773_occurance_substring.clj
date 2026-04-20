(defn occurance_substring
  "	Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
  [text pattern]
  (when (and (string? text)
             (string? pattern)
             (not (empty? pattern)))
    (let [pattern-len (count pattern)
          text-len (count text)
          positions (->> (range 0 (inc (- text-len pattern-len)))
                         (filter #(= pattern (subs text % (+ % pattern-len))))
                         seq)]
      (when positions
        {:occurrence (count positions)
         :position (vec positions)}))))