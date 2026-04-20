(defn tetrahedral_number
  "Write a function to find the nth tetrahedral number."
  [n]
  (if (or (nil? n) (< n 1))
    0
    (/ (* n (inc n) (+ n 2)) 6)))