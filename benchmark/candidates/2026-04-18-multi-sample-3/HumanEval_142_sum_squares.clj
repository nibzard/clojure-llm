(defn sum_squares
  ""
  [lst]
  (reduce
   +
   0
   (map-indexed
    (fn [idx x]
      (cond
        (zero? (mod idx 3)) (* x x)
        (zero? (mod idx 4)) (* x x x)
        :else x))
    (or lst []))))