(defn diagonal-sum?
  "Return true if a square matrix has equal main-diagonal and anti-diagonal sums.

  Examples:
  (diagonal-sum? [[1 2 3] [4 5 6] [7 8 9]]) ;=> true
  (diagonal-sum? [[2 0] [0 3]]) ;=> false
  (diagonal-sum? nil) ;=> false"
  [my_matrix]
  (let [n (count my_matrix)]
    (and (pos? n)
         (every? #(= n (count %)) my_matrix)
         (= (reduce + (map-indexed (fn [i row] (nth row i)) my_matrix))
            (reduce + (map-indexed (fn [i row] (nth row (- (dec n) i))) my_matrix))))))