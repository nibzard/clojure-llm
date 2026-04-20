(defn sort_array
  [arr]
  (vec (sort-by (juxt #(Long/bitCount %) identity) arr)))