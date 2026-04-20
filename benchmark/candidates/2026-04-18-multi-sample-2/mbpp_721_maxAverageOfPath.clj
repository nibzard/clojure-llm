(defn maxAverageOfPath
  "	Given a square matrix of size N*N given as a list of lists, where each cell is associated with a specific cost. A path is defined as a specific sequence of cells that starts from the top-left cell move only right or down and ends on bottom right cell. We want to find a path with the maximum average over all existing paths. Average is computed as total cost divided by the number of cells visited in the path."
  [cost]
  (let [rows (vec (map vec cost))
        n    (count rows)]
    (cond
      (zero? n) nil
      (some #(not= n (count %)) rows) nil
      :else
      (let [dp (reduce
                 (fn [acc i]
                   (assoc acc i
                          (reduce
                            (fn [row-acc j]
                              (let [v (get-in rows [i j])
                                    best-sum (cond
                                               (and (zero? i) (zero? j)) v
                                               (zero? i) (+ v (get row-acc (dec j)))
                                               (zero? j) (+ v (get-in acc [(dec i) j]))
                                               :else (+ v (max (get row-acc (dec j))
                                                               (get-in acc [(dec i) j]))))]
                                (assoc row-acc j best-sum)))
                            {}
                            (range n))))
                 {}
                 (range n))
            total-sum (get-in dp [(dec n) (dec n)])
            cells-visited (dec (+ n n))]
        (/ total-sum cells-visited)))))