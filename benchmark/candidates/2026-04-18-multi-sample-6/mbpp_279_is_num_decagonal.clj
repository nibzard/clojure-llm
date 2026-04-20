(defn is_num_decagonal
  "	Write a function to find the nth decagonal number."
  [n]
  (when (number? n)
    (/ (* n (- (* 8 n) 6)) 2)))