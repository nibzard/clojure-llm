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
  Return an ordered list of the values on the cells that the minimum path go through."
  [grid k]
  (let [n (count grid)
        value->pos (reduce-kv
                     (fn [m i row]
                       (reduce-kv (fn [m2 j v] (assoc m2 v [i j])) m row))
                     {}
                     (vec grid))
        dirs [[-1 0] [1 0] [0 -1] [0 1]]
        neighbors (fn [[i j]]
                    (reduce (fn [acc [di dj]]
                              (let [ni (+ i di)
                                    nj (+ j dj)]
                                (if (and (<= 0 ni) (< ni n) (<= 0 nj) (< nj n))
                                  (conj acc [ni nj])
                                  acc)))
                            []
                            dirs))
        cells (mapv value->pos (range 1 (inc (* n n))))
        reachable? (fn [a b steps]
                     (let [[r1 c1] a
                           [r2 c2] b
                           d (+ (Math/abs (long (- r1 r2)))
                                (Math/abs (long (- c1 c2))))]
                       (and (<= d steps)
                            (or (> steps d)
                                (zero? (mod (- steps d) 2))))))
        build (fn build [pos remaining]
                (if (= remaining 1)
                  [(get-in grid pos)]
                  (let [curr (get-in grid pos)
                        future-steps (dec remaining)
                        next-val (first
                                   (for [v (range 1 (inc (* n n)))
                                         :let [p (value->pos v)]
                                         :when (some #(and (= p %)
                                                           (reachable? p p (dec future-steps)))
                                                     (neighbors pos))]
                                     v))
                        next-pos (or
                                   (some (fn [p]
                                           (when (= (get-in grid p) next-val) p))
                                         (neighbors pos))
                                   (first
                                    (for [p (neighbors pos)
                                          :when (reachable? p (value->pos next-val) (dec future-steps))]
                                      p)))]
                    (into [curr] (build next-pos (dec remaining))))))]
    (let [start-pos (value->pos 1)]
      (vec (build start-pos k)))))