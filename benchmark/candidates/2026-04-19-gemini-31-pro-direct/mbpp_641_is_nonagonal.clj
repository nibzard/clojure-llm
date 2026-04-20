(defn is_nonagonal
  "Write a function to find the nth nonagonal number."
  [n]
  (/ (* n (- (* 7 n) 5)) 2))