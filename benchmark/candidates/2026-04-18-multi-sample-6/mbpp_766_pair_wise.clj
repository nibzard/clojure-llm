(defn pair_wise
  "	Write a function to return a list of all pairs of consecutive items in a given list."
  [l1]
  (map list l1 (rest l1)))