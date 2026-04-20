(defn find_literals
  "	Write a function to search a string for a regex pattern. The function should return the matching subtring, a start index and an end index."
  [text pattern]
  (let [re (re-pattern pattern)
        m  (re-find re text)]
    (if m
      {:match m
       :start (.-index (re-matcher re text))
       :end (+ (.-index (re-matcher re text)) (count m))}
      nil)))