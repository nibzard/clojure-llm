(defn get_row
  [lst x]
  (->> lst
       (map-indexed
         (fn [row-idx row]
           (->> row
                (map-indexed
                  (fn [col-idx val]
                    (when (= val x)
                      [row-idx col-idx])))
                (remove nil?))))
       (apply concat)
       (sort-by (juxt first #(- %2 0)))))