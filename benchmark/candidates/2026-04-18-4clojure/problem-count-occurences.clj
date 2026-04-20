(defn problem-count-occurences [a]
  (reduce (fn [m x] (assoc m x (inc (get m x 0)))) {} a))