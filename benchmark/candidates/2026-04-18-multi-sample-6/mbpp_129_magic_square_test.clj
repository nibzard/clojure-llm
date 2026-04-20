(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows (when (sequential? my_matrix) (vec my_matrix))
        n (count rows)]
    (and
     (pos? n)
     (every? sequential? rows)
     (every? #(= n (count %)) rows)
     (let [row-sums  (mapv #(reduce + 0 %) rows)
           target    (first row-sums)
           col-sums  (mapv (fn [col-idx]
                             (reduce + 0 (map #(nth % col-idx) rows)))
                           (range n))
           diag1-sum (reduce + 0 (map #(nth (nth rows %) %) (range n)))
           diag2-sum (reduce + 0 (map #(nth (nth rows %) (- (dec n) %)) (range n)))]
       (and
        (every? #(= target %) row-sums)
        (every? #(= target %) col-sums)
        (= target diag1-sum diag2-sum))))))