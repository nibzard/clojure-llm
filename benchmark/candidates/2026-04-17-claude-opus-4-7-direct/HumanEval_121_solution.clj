(defn solution
  "Given a non-empty list of integers, return the sum of all of the odd elements that are in even positions."
  [lst]
  (->> lst
       (map-indexed vector)
       (filter (fn [[idx val]] (and (even? idx) (odd? val))))
       (map second)
       (reduce + 0)))