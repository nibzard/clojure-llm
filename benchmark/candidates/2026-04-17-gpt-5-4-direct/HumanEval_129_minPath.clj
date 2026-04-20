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
        dirs [[1 0] [-1 0] [0 1] [0 -1]]
        pos-of (reduce-kv
                (fn [m r row]
                  (reduce-kv (fn [m2 c v] (assoc m2 v [r c])) m row))
                {}
                (vec grid))
        neighbors (fn [[r c]]
                    (reduce
                     (fn [acc [dr dc]]
                       (let [nr (+ r dr)
                             nc (+ c dc)]
                         (if (and (<= 0 nr) (< nr n) (<= 0 nc) (< nc n))
                           (conj acc [nr nc])
                           acc)))
                     []
                     dirs))
        can-finish?
        (memoize
         (fn [pos rem target]
           (cond
             (= rem 0) true
             (some #(= target (get-in grid %)) (neighbors pos)) true
             :else (boolean
                    (some #(can-finish? % (dec rem) target)
                          (neighbors pos))))))
        step
        (fn [states rem]
          (let [candidate-values
                (sort
                 (into #{}
                       (mapcat
                        (fn [pos]
                          (map #(get-in grid %) (neighbors pos)))
                        states)))
                best-v
                (first
                 (filter
                  (fn [v]
                    (let [target-pos (pos-of v)]
                      (some #(can-finish? % (dec rem) v) states)))
                  candidate-values))]
            [(pos-of best-v) best-v]))]
    (loop [states [(pos-of 1)]
           rem (dec k)
           path [1]]
      (if (zero? rem)
        path
        (let [[next-state v] (step states rem)]
          (recur [next-state] (dec rem) (conj path v)))))))