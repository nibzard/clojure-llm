(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [rows (seq my_matrix)
        n    (count rows)]
    (and
      (pos? n)
      (every? #(and (sequential? %) (= n (count %))) rows)
      (let [target-sum (reduce + (first rows))
            row-sums   (map #(reduce + %) rows)
            col-sums   (apply map + rows)
            diag1-sum  (reduce + (map-indexed (fn [i row] (nth row i)) rows))
            diag2-sum  (reduce + (map-indexed (fn [i row] (nth row (- n 1 i))) rows))]
        (and
          (every? #(= target-sum %) row-sums)
          (every? #(= target-sum %) col-sums)
          (= target-sum diag1-sum diag2-sum)))))