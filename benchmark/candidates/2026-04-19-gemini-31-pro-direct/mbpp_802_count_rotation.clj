(defn count_rotation [arr]
  (if (empty? arr)
    0
    (let [min-val (reduce min arr)]
      (first (keep-indexed #(when (= %2 min-val) %1) arr)))))