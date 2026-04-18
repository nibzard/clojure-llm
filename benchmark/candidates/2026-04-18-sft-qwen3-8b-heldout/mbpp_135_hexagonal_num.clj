(defn hexagonal_num
  "	Write a function to find the nth hexagonal number."
  [n]
  (when (number? n)
    (* n (inc n))))