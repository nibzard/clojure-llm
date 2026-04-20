(defn maxAverageOfPath
  "	Given a square matrix of size N*N given as a list of lists, where each cell is associated with a specific cost. A path is defined as a specific sequence of cells that starts from the top-left cell move only right or down and ends on bottom right cell. We want to find a path with the maximum average over all existing paths. Average is computed as total cost divided by the number of cells visited in the path."
  [cost]
  (let [rows (mapv #(mapv (fn [x] (if (number? x) x 0)) %) (or cost []))
        n    (count rows)]
    (cond
      (zero? n) 0
      (some #(not= n (count %)) rows) 0
      :else
      (let [dp (reduce
                 (fn [dp-row i]
                   (reduce
                    (fn [row j]
                      (let [v  (get-in rows [i j] 0)
                            up   (when (pos? i) (get-in dp-row [(dec i) j]))
                            left (when (pos? j) (get row (dec j)))
                            best (cond
                                   (and (zero? i) (zero? j)) v
                                   (nil? up) (+ left v)
                                   (nil? left) (+ up v)
                                   :else (+ (max up left) v))]
                        (assoc row j best)))
                    (vec (repeat n nil))
                    (range n)))
                 []
                 (range n))
            total (get-in dp [(dec n) (dec n)])
            cells (dec (+ n n))]
        (/ total cells)))))