(defn sum_squares
  [lst]
  (->> lst
       (map-indexed (fn [idx val]
                      (cond
                        (zero? (mod idx 3)) (* val val)
                        (zero? (mod idx 4)) (* val val val)
                        :else val)))
       (reduce + 0)))