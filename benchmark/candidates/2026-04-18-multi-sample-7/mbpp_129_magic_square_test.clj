(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows (and (sequential? my_matrix) (seq my_matrix) my_matrix)
        n (count rows)]
    (boolean
     (and rows
          (every? sequential? rows)
          (every? #(= n (count %)) rows)
          (let [target (reduce + (first rows))
                row-sums (map #(reduce + %) rows)
                col-sums (apply map + rows)
                diag1 (reduce + (map-indexed (fn [i row] (nth row i)) rows))
                diag2 (reduce + (map-indexed (fn [i row] (nth row (- n 1 i))) rows))]
            (and (every? #(= target %) row-sums)
                 (every? #(= target %) col-sums)
                 (= target diag1 diag2))))))