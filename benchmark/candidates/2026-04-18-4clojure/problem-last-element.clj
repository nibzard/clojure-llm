(defn problem-last-element [a]
  (if (next a)
    (recur (next a))
    (first a)))