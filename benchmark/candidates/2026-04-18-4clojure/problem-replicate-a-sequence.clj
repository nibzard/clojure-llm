(defn problem-replicate-a-sequence [a b]
  (mapcat #(repeat b %) a))