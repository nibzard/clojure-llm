(defn sort_counter
  "	Write a function to sort a map by value."
  [dict1]
  (into (sorted-map-by (fn [k1 k2]
                         (let [v1 (get dict1 k1)
                               v2 (get dict1 k2)]
                           (compare [v1 k1] [v2 k2]))))
        dict1))