(defn largest_smallest_integers
  [lst]
  (let [negatives (filter neg? lst)
        positives (filter pos? lst)]
    [(when (seq negatives) (apply max negatives))
     (when (seq positives) (apply min positives))]))