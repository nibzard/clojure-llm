(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows (when (sequential? my_matrix) (seq my_matrix))
        n (count rows)]
    (and rows
         (pos? n)
         (every? sequential? rows)
         (every? #(= n (count %)) rows)
         (let [target (reduce + (first rows))
               row-sums (map #(reduce + %) rows)
               col-sums (for [j (range n)]
                          (reduce + (map #(nth % j) rows)))
               diag1 (reduce + (map #(nth (nth rows %) %) (range n)))
               diag2 (reduce + (map #(nth (nth rows %) (- n 1 %)) (range n)))]
           (and (every? #(= target %) row-sums)
                (every? #(= target %) col-sums)
                (= target diag1 diag2))))))