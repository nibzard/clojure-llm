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
        pos-by-value (object-array (inc (* n n)))]
    (doseq [r (range n)
            c (range n)]
      (aset pos-by-value (get-in grid [r c]) [r c]))
    (letfn [(neighbors [[r c]]
              (cond-> []
                (> r 0) (conj [(dec r) c])
                (< r (dec n)) (conj [(inc r) c])
                (> c 0) (conj [r (dec c)])
                (< c (dec n)) (conj [r (inc c)])))
            (best-from [positions steps-left]
              (if (= steps-left 1)
                [(apply min-key #(get-in grid %) positions)]
                (let [min-val (apply min (map #(get-in grid %) positions))
                      best-positions (filter #(= min-val (get-in grid %)) positions)
                      next-positions (distinct (mapcat neighbors best-positions))
                      suffix (best-from next-positions (dec steps-left))]
                  (into [(first suffix)] (rest suffix))))]
            (build [positions steps-left]
              (if (= steps-left 1)
                [(apply min (map #(get-in grid %) positions))]
                (let [min-val (apply min (map #(get-in grid %) positions))
                      best-positions (filter #(= min-val (get-in grid %)) positions)
                      next-positions (distinct (mapcat neighbors best-positions))]
                  (into [min-val] (build next-positions (dec steps-left))))))]
      (build (map #(aget pos-by-value %) (range 1 (inc (* n n)))) k))))