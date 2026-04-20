(defn tetrahedral_number
  "	Write a function to find the nth tetrahedral number."
  [n]
  (when (some? n)
    (/ (* n (inc n) (+ n 2)) 6)))