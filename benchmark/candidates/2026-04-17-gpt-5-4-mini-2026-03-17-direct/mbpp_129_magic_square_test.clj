(defn magic_square_test
  "Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [n (count my_matrix)
        rows my_matrix
        cols (apply map vector my_matrix)
        diag1 (map-indexed (fn [i row] (nth row i)) my_matrix)
        diag2 (map-indexed (fn [i row] (nth row (- (dec n) i))) my_matrix)
        target (when (pos? n) (reduce + (first rows)))]
    (and (pos? n)
         (every? #(= n (count %)) rows)
         (every? #(= target (reduce + %)) rows)
         (every? #(= target (reduce + %)) cols)
         (= target (reduce + diag1))
         (= target (reduce + diag2)))))