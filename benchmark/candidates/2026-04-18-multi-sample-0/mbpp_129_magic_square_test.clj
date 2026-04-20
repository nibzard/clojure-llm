(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows (seq my_matrix)]
    (and rows
         (every? sequential? rows)
         (let [n (count rows)]
           (and (pos? n)
                (every? #(= n (count %)) rows)
                (let [row-sums  (map #(reduce + %) rows)
                      col-sums  (apply map + rows)
                      diag1-sum (reduce + (map #(nth %1 %2) rows (range n)))
                      diag2-sum (reduce + (map #(nth %1 %2) rows (reverse (range n))))
                      target    (first row-sums)]
                  (and (every? #(= target %) row-sums)
                       (every? #(= target %) col-sums)
                       (= target diag1-sum diag2-sum)))))))