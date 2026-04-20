(defn sum_squares
  ""
  [lst]
  (->> lst
       (map-indexed (fn [idx x]
                      (cond
                        (zero? (mod idx 3)) (* x x)
                        (and (zero? (mod idx 4)) (not (zero? (mod idx 3)))) (* x x x)
                        :else x)))
       (reduce + 0)))