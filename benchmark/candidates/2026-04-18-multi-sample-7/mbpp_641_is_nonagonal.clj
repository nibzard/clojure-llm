(defn is_nonagonal
  "	Write a function to find the nth nonagonal number."
  [n]
  (when (some? n)
    (/ (- (* 7 n n) (* 5 n)) 2)))