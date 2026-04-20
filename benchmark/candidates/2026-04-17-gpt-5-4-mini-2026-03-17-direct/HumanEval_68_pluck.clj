(defn pluck
  [arr]
  (if-let [[idx val] (first (sort-by (fn [[i v]] [v i])
                                     (keep-indexed (fn [i v]
                                                    (when (even? v)
                                                      [i v]))
                                                  arr)))]
    [val idx]
    []))