(defn magic_square_test
  "Write a function to calculate whether the matrix is a magic square."
  [my_matrix]
  (if (empty? my_matrix)
    false
    (let [n (count my_matrix)]
      (if (not-every? #(= n (count %)) my_matrix)
        false
        (let [target (apply + (first my_matrix))
              rows-sums (map #(apply + %) my_matrix)
              cols-sums (map #(apply + %) (apply map vector my_matrix))
              diag1-sum (apply + (map-indexed #(nth %2 %1) my_matrix))
              diag2-sum (apply + (map-indexed #(nth %2 (- n 1 %1)) my_matrix))
              all-sums (concat rows-sums cols-sums [diag1-sum diag2-sum])]
          (every? #(= target %) all-sums))))))