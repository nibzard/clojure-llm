(defn occurance_substring
  "	Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
  [text pattern]
  (when (and (string? text)
             (string? pattern)
             (not (empty? pattern))
             (<= (count pattern) (count text)))
    (let [plen (count pattern)
          positions (keep (fn [i]
                            (when (= pattern (subs text i (+ i plen)))
                              i))
                          (range (inc (- (count text) plen))))]
      (when (seq positions)
        {:occurrence (count positions)
         :position   (vec positions)}))))