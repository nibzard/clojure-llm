(defn problem-count-a-sequence [a]
  (reduce (fn [n _] (inc n)) 0 a))