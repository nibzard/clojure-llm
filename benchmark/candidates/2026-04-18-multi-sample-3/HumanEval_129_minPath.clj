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
                    (fn [m r row]
                      (reduce-kv (fn [m2 c v] (assoc m2 v [r c])) m row))
                    {}
                    (vec grid))
        neighbors (fn [[r c]]
                    (filter (fn [[nr nc]]
                              (and (<= 0 nr) (< nr n) (<= 0 nc) (< nc n)))
                            [[(dec r) c] [(inc r) c] [r (dec c)] [r (inc c)]]))
        step-possible? (fn [a b]
                         (let [[ra ca] (value->pos a)
                               [rb cb] (value->pos b)]
                           (= 1 (+ (Math/abs (long (- ra rb)))
                                   (Math/abs (long (- ca cb)))))))
        best-from
        (memoize
         (fn best-from [pos len]
           (let [v (get-in grid pos)]
             (if (= len 1)
               [v]
               (into [v]
                     (apply min-key seq
                            (map #(best-from % (dec len)) (neighbors pos))))))))]
    (if (= k 1)
      [(apply min (keys value->pos))]
      (let [sorted-values (sort (keys value->pos))
            result (loop [prefix []
                          candidates sorted-values
                          remaining k]
                     (if (= remaining 0)
                       prefix
                       (let [chosen
                             (first
                              (for [v candidates
                                    :let [new-prefix (conj prefix v)]
                                    :when
                                    (if (= remaining 1)
                                      true
                                      (let [next-cands
                                            (sort
                                             (set
                                              (mapcat
                                               (fn [cv]
                                                 (map #(get-in grid %) (neighbors (value->pos cv))))
                                               [v])))]
                                        (or (empty? next-cands)
                                            true)))]
                                v))
                             new-prefix (conj prefix chosen)]
                         (if (= remaining 1)
                           new-prefix
                           (let [next-values (sort (map #(get-in grid %) (neighbors (value->pos chosen))))
                                 suffixes (map #(best-from (value->pos %) (dec remaining)) next-values)
                                 best-suffix (apply min-key seq suffixes)]
                             (into prefix (cons chosen (rest best-suffix)))))))]
        (vec result)))))