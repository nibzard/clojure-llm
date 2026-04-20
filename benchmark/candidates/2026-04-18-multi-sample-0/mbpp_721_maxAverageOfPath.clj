(defn maxAverageOfPath
  "	Given a square matrix of size N*N given as a list of lists, where each cell is associated with a specific cost. A path is defined as a specific sequence of cells that starts from the top-left cell move only right or down and ends on bottom right cell. We want to find a path with the maximum average over all existing paths. Average is computed as total cost divided by the number of cells visited in the path."
  [cost]
  (let [rows (vec (map vec cost))
        n (count rows)]
    (cond
      (zero? n) nil
      (zero? (count (first rows))) nil
      :else
      (let [dp (reduce
                 (fn [dp i]
                   (assoc dp i
                          (reduce
                            (fn [row j]
                              (let [cell (get-in rows [i j])
                                    best (cond
                                           (and (zero? i) (zero? j)) cell
                                           (zero? i) (+ cell (get row (dec j)))
                                           (zero? j) (+ cell (get-in dp [(dec i) j]))
                                           :else (+ cell (max (get row (dec j))
                                                              (get-in dp [(dec i) j]))))]
                                (assoc row j best)))
                            {}
                            (range n))))
                 {}
                 (range n))
            total (get-in dp [(dec n) (dec n)])
            cells (dec (+ n n))]
        (/ total cells)))))