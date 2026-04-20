(defn sort_counter
  "	Write a function to sort a map by value."
  [dict1]
  (into (sorted-map-by (fn [k1 k2]
                         (let [v1 (get dict1 k1)
                               v2 (get dict1 k2)
                               c (compare v1 v2)]
                           (if (zero? c)
                             (compare k1 k2)
                             c))))
        dict1))