(defn prod_signs
  [arr]
  (when (seq arr)
    (let [sum-mags (reduce + (map #(Math/abs (long %)) arr))
          prod-signs (reduce * (map #(compare % 0) arr))]
      (* sum-mags prod-signs))))