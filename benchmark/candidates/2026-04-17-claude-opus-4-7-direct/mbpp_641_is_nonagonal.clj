(defn is_nonagonal
  "Write a function to find the nth nonagonal number."
  [n]
  (when (and (integer? n) (pos? n))
    (* n (- (* 7 n) 6) 1/2)))