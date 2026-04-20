(defn hexagonal_num
  "	Write a function to find the nth hexagonal number."
  [n]
  (when (some? n)
    (* n (dec (* 2 n)))))