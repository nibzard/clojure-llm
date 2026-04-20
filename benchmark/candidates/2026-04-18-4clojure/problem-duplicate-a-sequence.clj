(defn problem-duplicate-a-sequence [a]
  (into (empty a) (mapcat #(list % %) a)))