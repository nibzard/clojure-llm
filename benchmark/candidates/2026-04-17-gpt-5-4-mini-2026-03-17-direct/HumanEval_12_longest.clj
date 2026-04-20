(defn longest
  "Out of list of strings, return the longest one. Return the first one in case of multiple
  strings of the same length. Return nil in case the input list is empty."
  [strings]
  (when-let [s (seq strings)]
    (reduce (fn [best current]
              (if (> (count current) (count best))
                current
                best))
            (first s)
            (rest s))))