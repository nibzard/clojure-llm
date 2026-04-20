(defn problem-pack-a-sequence [a]
  (reduce
    (fn [acc x]
      (if (and (seq acc) (= x (last (last acc))))
        (concat (butlast acc) [(concat (last acc) [x])])
        (concat acc [(list x)])))
    '()
    a))