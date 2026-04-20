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
        pos-by-val (reduce-kv
                    (fn [m i row]
                      (reduce-kv (fn [m2 j v] (assoc m2 v [i j])) m row))
                    {}
                    (vec grid))
        neighbors (fn [[i j]]
                    (filter (fn [[x y]] (and (<= 0 x) (< x n) (<= 0 y) (< y n)))
                            [[(dec i) j] [(inc i) j] [i (dec j)] [i (inc j)]]))
        best-from
        (letfn [(solve [pos len]
                  (if (= len 1)
                    [(get-in grid pos)]
                    (let [cands (map #(solve % (dec len)) (neighbors pos))
                          best-tail (reduce
                                     (fn [a b]
                                       (if (neg? (compare a b)) a b))
                                     (first cands)
                                     (rest cands))]
                      (into [(get-in grid pos)] best-tail))))]
          (memoize solve))
        all-positions (vals pos-by-val)]
    (reduce
     (fn [a b]
       (if (neg? (compare a b)) a b))
     (map #(best-from % k) all-positions)))