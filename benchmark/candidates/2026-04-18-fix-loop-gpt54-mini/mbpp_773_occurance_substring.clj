(defn occurance_substring
  "Write a function to find the occurrence and position of the substrings within a string. Return nil if there is no match."
  [text pattern]
  (let [tlen (count text)
        plen (count pattern)]
    (cond
      (or (nil? text) (nil? pattern)) nil
      (> plen tlen) nil
      :else
      (let [positions (loop [idx 0
                             acc []]
                        (if (> idx (- tlen plen))
                          acc
                          (if (= pattern (subs text idx (+ idx plen)))
                            (recur (inc idx) (conj acc idx))
                            (recur (inc idx) acc))))]
        (when (seq positions)
          {:occurrence (count positions)
           :position positions})))))