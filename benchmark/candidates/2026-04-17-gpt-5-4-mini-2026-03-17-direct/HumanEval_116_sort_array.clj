(defn sort_array
  [arr]
  (sort-by (fn [n] [(Long/bitCount (long n)) n]) arr))