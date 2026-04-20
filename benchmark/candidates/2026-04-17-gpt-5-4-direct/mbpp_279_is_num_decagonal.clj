(defn is_num_decagonal
  "Write a function to find the nth decagonal number."
  [n]
  (when (some? n)
    (/ (* n (- (* 8 n) 6)) 2)))