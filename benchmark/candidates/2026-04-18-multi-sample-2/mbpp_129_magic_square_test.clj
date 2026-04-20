(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows (when (sequential? my_matrix) (seq my_matrix))
        n (count rows)]
    (and
      (pos? n)
      (every? #(and (sequential? %) (= (count %) n)) rows)
      (let [row-sums  (map #(reduce + %) rows)
            target    (first row-sums)
            col-sums  (apply map (fn [& xs] (reduce + xs)) rows)
            diag1-sum (reduce + (map #(nth (nth rows %) %) (range n)))
            diag2-sum (reduce + (map #(nth (nth rows %) (- n 1 %)) (range n)))
            all-sums  (concat row-sums col-sums [diag1-sum diag2-sum])]
        (every? #(= target %) all-sums)))))