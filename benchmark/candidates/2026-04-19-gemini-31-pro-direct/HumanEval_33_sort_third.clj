(defn sort_third [l]
  (let [sorted-thirds (vec (sort (take-nth 3 l)))]
    (vec (map-indexed (fn [i x]
                        (if (zero? (mod i 3))
                          (sorted-thirds (quot i 3))
                          x))
                      l))))