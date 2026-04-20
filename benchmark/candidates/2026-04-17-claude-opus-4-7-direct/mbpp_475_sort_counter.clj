(defn sort_counter
  "Write a function to sort a map by value."
  [dict1]
  (into (sorted-map-by (fn [key1 key2]
                         (compare [(get dict1 key1) key1]
                                  [(get dict1 key2) key2])))
        dict1))