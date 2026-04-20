(defn sum_squares [lst]
  (reduce + 0
          (map-indexed
           (fn [i x]
             (cond
               (zero? (mod i 3)) (* x x)
               (zero? (mod i 4)) (* x x x)
               :else x))
           lst)))