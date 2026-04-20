(defn minPath
  [grid k]
  (let [n (count grid)
        get-val (fn [r c] (nth (nth grid r) c))
        neighbors (fn [r c]
                    (filter (fn [[nr nc]]
                              (and (>= nr 0) (< nr n)
                                   (>= nc 0) (< nc n)))
                            [[(dec r) c] [(inc r) c]
                             [r (dec c)] [r (inc c)]]))
        start-val (apply min (map first grid))
        start-rows (filter (fn [r] (= start-val (get-val r 0))) (range n))
        start-cols (filter (fn [c] (= start-val (get-val 0 c))) (range n))
        start-cells (set (concat (map vector start-rows (repeat 0))
                                 (map vector (repeat 0) start-cols)))
        init-paths (map (fn [[r c]] [start-val r c]) start-cells)]
    (loop [paths init-paths
           step 1]
      (if (= step k)
        (map first paths)
        (let [next-paths
              (->> (mapcat (fn [[val r c]]
                             (map (fn [[nr nc]]
                                    [(get-val nr nc) nr nc])
                                  (neighbors r c)))
                           paths)
                   (sort-by first)
                   (take (count paths)))]
          (recur next-paths (inc step)))))))