(defn tetrahedral_number
  "	Write a function to find the nth tetrahedral number."
  [n]
  (if (and (number? n) (not (neg? n)))
    (/ (* n (inc n) (+ n 2)) 6)
    nil))