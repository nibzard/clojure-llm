(defn hexagonal_num
  "	Write a function to find the nth hexagonal number."
  [n]
  (when (some? n)
    (* n (- (* 2 n) 1))))