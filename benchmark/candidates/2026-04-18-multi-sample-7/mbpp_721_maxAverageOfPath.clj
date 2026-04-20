(defn maxAverageOfPath
  "	Given a square matrix of size N*N given as a list of lists, where each cell is associated with a specific cost. A path is defined as a specific sequence of cells that starts from the top-left cell move only right or down and ends on bottom right cell. We want to find a path with the maximum average over all existing paths. Average is computed as total cost divided by the number of cells visited in the path."
  [cost]
  (let [grid (mapv #(mapv (fn [x] (if (number? x) x 0)) %) (or cost []))
        n    (count grid)]
    (cond
      (zero? n) 0
      (some #(not= n (count %)) grid) 0
      :else
      (let [dp (reduce
                (fn [rows i]
                  (conj rows
                        (reduce
                         (fn [row j]
                           (let [v  (get-in grid [i j])
                                 up (when (pos? i) (get-in rows [(dec i) j]))
                                 lf (when (pos? j) (nth row (dec j)))
                                 best (cond
                                        (and up lf) (max up lf)
                                        up up
                                        lf lf
                                        :else 0)]
                             (conj row (+ v best))))
                         []
                         (range n))))
                []
                (range n))
            total (get-in dp [(dec n) (dec n)])
            cells (dec (+ n n))]
        (/ total cells)))))