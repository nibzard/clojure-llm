(defn problem-flatten-a-sequence [a]
  (letfn [(flat [x]
            (if (sequential? x)
              (mapcat flat x)
              (list x)))]
    (flat a)))