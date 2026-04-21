(defn tetrahedral_number
  "Write a function to find the nth tetrahedral number."
  [n]
  (/ (* n (+ n 1) (+ n 2)) 6))