(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows (seq my_matrix)
        n (count my_matrix)]
    (and
      rows
      (every? sequential? my_matrix)
      (every? #(= n (count %)) my_matrix)
      (let [target (reduce + (first my_matrix))
            cols   (apply map vector my_matrix)
            diag1  (map-indexed (fn [i row] (nth row i)) my_matrix)
            diag2  (map-indexed (fn [i row] (nth row (- n 1 i))) my_matrix)]
        (and
          (every? #(= target (reduce + %)) my_matrix)
          (every? #(= target (reduce + %)) cols)
          (= target (reduce + diag1))
          (= target (reduce + diag2))))))