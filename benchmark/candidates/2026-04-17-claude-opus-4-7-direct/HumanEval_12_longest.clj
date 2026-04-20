(defn longest
  "Out of list of strings, return the longest one. Return the first one in case of multiple
  strings of the same length. Return nil in case the input list is empty."
  [strings]
  (when (seq strings)
    (reduce (fn [current longest]
              (if (> (count current) (count longest))
                current
                longest))
            strings)))