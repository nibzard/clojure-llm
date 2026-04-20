(defn sort_matrix
  "Write a function to sort a given matrix in ascending order according to the sum of its rows."
  [M]
  (sort-by #(reduce + 0 %) M))