(defn maxAverageOfPath
  "Given a square matrix of size N*N, finds the path from top-left to bottom-right
  (moving only right or down) that has the maximum average cost."
  [cost]
  (if (or (nil? cost) (empty? cost) (empty? (first cost)))
    0.0
    (let [n (count cost)
          m (count (first cost))
          ;; dp[i][j] stores the maximum sum achievable to reach cell (i, j)
          ;; We use a transient vector of vectors for O(1) updates
          dp (loop [i 0
                   acc (transient [])]
               (if (= i n)
                 (persistent! acc)
                 (recur (inc i)
                        (conj! acc (loop [j 0
                                         row (transient [])]
                                    (if (= j m)
                                      (persistent! row)
                                      (recur (inc j)
                                             (conj! row (cond
                                                          (and (zero? i) (zero? j)) (get-in cost [i j])
                                                          (zero? i) (+ (get-in cost [i j]) (get-in acc [(dec i) j]))
                                                          (zero? j) (+ (get-in cost [i j]) (get-in acc [i (dec j)]))
                                                          :else (+ (get-in cost [i j])
                                                                   (max (get-in acc [(dec i) j])
                                                                        (get-in acc [i (dec j)]))))))))))))]
      ;; The number of cells visited in any path from (0,0) to (n-1, m-1)
      ;; moving only right and down is exactly n + m - 1.
      (/ (get-in dp [(dec n) (dec m)])
         (double (+ n m -1))))))