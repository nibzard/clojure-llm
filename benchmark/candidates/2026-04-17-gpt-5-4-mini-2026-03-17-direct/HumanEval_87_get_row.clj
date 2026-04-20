(defn get_row
  [lst x]
  (->> lst
       (map-indexed
        (fn [row-idx row]
          (->> row
               (map-indexed vector)
               (filter (fn [[_ v]] (= v x)))
               (map (fn [[col-idx _]] [row-idx col-idx]))
               (sort-by second >))))
       (apply concat)
       vec))