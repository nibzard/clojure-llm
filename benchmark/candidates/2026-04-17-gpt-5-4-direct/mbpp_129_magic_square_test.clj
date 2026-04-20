(defn magic_square_test
  "	Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (let [m (when (sequential? my_matrix) (mapv #(when (sequential? %) (vec %)) my_matrix))
        n (count m)]
    (and
      (pos? n)
      (every? some? m)
      (every? #(= n (count %)) m)
      (let [rows m
            cols (apply mapv vector rows)
            diag1 (mapv #(get-in rows [% %]) (range n))
            diag2 (mapv #(get-in rows [% (- (dec n) %)]) (range n))
            sums (map #(reduce + %) (concat rows cols [diag1 diag2]))]
        (apply = sums)))))