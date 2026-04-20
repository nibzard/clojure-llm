(defn sort_matrix
  "Write a function to sort a given matrix in ascending order according to the sum of its rows."
  [M]
  (sort-by (fn [row] (apply + row)) M))