(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows (when (sequential? my_matrix) (mapv vec my_matrix))
        n (count rows)]
    (and
     (pos? n)
     (every? #(= n (count %)) rows)
     (let [diag1 (reduce + (map #(nth (rows %) %) (range n)))
           diag2 (reduce + (map #(nth (rows %) (- (dec n) %)) (range n)))
           target diag1
           row-sums (map #(reduce + %) rows)
           col-sums (map #(reduce + %) (apply map vector rows))]
       (and
        (= target diag2)
        (every? #(= target %) row-sums)
        (every? #(= target %) col-sums))))))