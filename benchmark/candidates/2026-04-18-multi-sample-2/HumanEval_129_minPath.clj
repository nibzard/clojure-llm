(defn minPath
  "Given a grid with N rows and N columns (N >= 2) and a positive integer k, 
  each cell of the grid contains a value. Every integer in the range [1, N * N]
  inclusive appears exactly once on the cells of the grid.
  You have to find the minimum path of length k in the grid. You can start
  from any cell, and in each step you can move to any of the neighbor cells,
  in other words, you can go to cells which share an edge with you current
  cell.
  Please note that a path of length k means visiting exactly k cells (not
  necessarily distinct).
  You CANNOT go off the grid.
  A path A (of length k) is considered less than a path B (of length k) if
  after making the ordered lists of the values on the cells that A and B go
  through (let's call them lst_A and lst_B), lst_A is lexicographically less
  than lst_B, in other words, there exist an integer index i (1 <= i <= k)
  such that lst_A[i] < lst_B[i] and for any j (1 <= j < i) we have
  lst_A[j] = lst_B[j].
  It is guaranteed that the answer is unique.
  Return an ordered list of the values on the cells that the minimum path go through.
  Examples:    
  >>> (minPath [[1 2 3] [4 5 6] [7 8 9]] 3)
  [1 2 1]
  >>> (minPath [[5 9 3] [4 1 6] [7 8 2]] 1)
  [1]"
  [grid k]
  (let [n (count grid)
        value->pos (reduce-kv
                    (fn [m i row]
                      (reduce-kv (fn [m2 j v] (assoc m2 v [i j])) m row))
                    {}
                    (vec grid))
        neighbors (fn [[i j]]
                    (cond-> []
                      (> i 0) (conj [(dec i) j])
                      (< i (dec n)) (conj [(inc i) j])
                      (> j 0) (conj [i (dec j)])
                      (< j (dec n)) (conj [i (inc j)])))
        cache (atom {})]
    (letfn [(best-from [pos steps]
              (if-let [res (get @cache [pos steps])]
                res
                (let [res (if (= steps 1)
                            [(get-in grid pos)]
                            (let [v (get-in grid pos)
                                  best-suffix (apply min-key identity
                                                     (map #(best-from % (dec steps))
                                                          (neighbors pos)))]
                              (into [v] best-suffix)))]
                  (swap! cache assoc [pos steps] res)
                  res)))]
      (apply min-key identity
             (map #(best-from (value->pos %) k)
                  (range 1 (inc (* n n))))))))