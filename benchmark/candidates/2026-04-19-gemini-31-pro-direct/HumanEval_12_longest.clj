(defn longest
  "Out of list of strings, return the longest one. Return the first one in case of multiple
  strings of the same length. Return nil in case the input list is empty."
  [strings]
  (reduce (fn [a b]
            (if (> (count b) (count a))
              b
              a))
          nil
          strings))