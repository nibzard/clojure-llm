(defn minPath [grid k]
  (let [n (count grid)
        ;; Find position of value 1
        [start-row start-col] 
        (first (for [i (range n)
                     j (range n)
                     :when (= 1 (get-in grid [i j]))]
                 [i j]))
        ;; Get valid neighbors of a position
        get-neighbors (fn [row col]
                        (filter (fn [[r c]]
                                  (and (>= r 0) (< r n)
                                       (>= c 0) (< c n)))
                                [[(dec row) col] [(inc row) col]
                                 [row (dec col)] [row (inc col)]]))
        ;; Build the path iteratively
        build-path (fn [path row col steps]
                     (if (= steps k)
                       path
                       (let [neighbors (get-neighbors row col)
                             [next-row next-col] 
                             (first (sort-by (fn [[r c]] (get-in grid [r c])) neighbors))]
                         (recur (conj path (get-in grid [next-row next-col])) 
                                next-row next-col (inc steps)))))]
    (build-path [1] start-row start-col 1)))