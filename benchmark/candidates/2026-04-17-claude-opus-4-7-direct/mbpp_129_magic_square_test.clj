(defn magic_square_test
  "Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows my_matrix
        cols (apply map vector rows)
        n (count rows)
        ;; Calculate the target sum from the first row
        target (reduce + (first rows))]
    (and
     ;; 1. Must be a square matrix (same number of rows and columns)
     (every? #(= n (count %)) rows)
     ;; 2. All rows must sum to target
     (every? #(= target (reduce + %)) rows)
     ;; 3. All columns must sum to target
     (every? #(= target (reduce + %)) cols)
     ;; 4. Main diagonal must sum to target
     (= target (reduce + (map nth rows (range n))))
     ;; 5. Anti-diagonal must sum to target
     (= target (reduce + (map nth rows (range (dec n) -1 -1)))))))