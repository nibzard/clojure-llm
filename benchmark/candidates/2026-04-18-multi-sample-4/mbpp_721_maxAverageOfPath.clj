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
                (fn [dp-row i]
                  (reduce
                   (fn [row j]
                     (let [cell (get-in rows [i j])
                           best (+ cell
                                   (cond
                                     (and (zero? i) (zero? j)) 0
                                     (zero? i) (nth row (dec j))
                                     (zero? j) (get-in dp-row [(dec i) j])
                                     :else (max (get-in dp-row [(dec i) j])
                                                (nth row (dec j)))))]
                       (conj row best)))
                   []
                   (range n)))
                []
                (range n))
            max-sum (get-in dp [(dec n) (dec n)])
            path-len (dec (+ n n))]
        (/ max-sum path-len))))