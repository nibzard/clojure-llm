(defn get_row
  "You are given a 2 dimensional data, as a nested lists,
  which is similar to matrix, however, unlike matrices,
  each row may contain a different number of columns.
  Given lst, and integer x, find integers x in the list,
  and return list of vectors, [(x1, y1), (x2, y2) ...] such that
  each vector is a coordinate - (row, columns), starting with 0.
  Sort coordinates initially by rows in ascending order.
  Also, sort coordinates of the row by columns in descending order.
  Examples:
  >>> (get_row [[1 2 3 4 5 6] [1 2 3 4 1 6] [1 2 3 4 5 1]] 1)
  [[0 0] [1 4] [1 0] [2 5] [2 0]]
  >>> (get_row [] 1)
  []
  >>> (get_row [[] [1] [1 2 3]] 3)
  [[2 2]]"
  [lst x]
  (vec
   (mapcat
    (fn [[row-idx row]]
      (map (fn [col-idx] [row-idx col-idx])
           (sort > (keep-indexed (fn [col-idx v] (when (= v x) col-idx)) row))))
    (map-indexed vector lst))))