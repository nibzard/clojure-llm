(defn solution
  "Given a non-empty list of integers, return the sum of all of the odd elements that are in even positions.
  Examples
  >>> (solution [5 8 7 1])
  12
  >>> (solution [3 3 3 3 3])
  9
  >>> (solution [30 13 24 321])
  0"
  [lst]
  (->> lst
       (map-indexed vector)
       (filter (fn [[idx x]]
                 (and (even? idx) (odd? x))))
       (map second)
       (reduce + 0)))