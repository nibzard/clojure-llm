(defn is_octagonal
  "	Write a function to find the nth octagonal number."
  [n]
  (when (number? n)
    (/ (* n (- (* 3 n) 2)) 1)))