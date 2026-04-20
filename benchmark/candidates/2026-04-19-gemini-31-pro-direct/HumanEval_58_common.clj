(defn common
  "Return sorted unique common elements for two lists."
  [l1 l2]
  (let [s2 (set l2)]
    (vec (sort (filter #(contains? s2 %) (set l1))))))