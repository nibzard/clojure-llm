(defn minPath
  [grid k]
  (let [n (count grid)
        dirs [[1 0] [-1 0] [0 1] [0 -1]]
        val-at (fn [r c] (nth (nth grid r) c))
        neighbors (fn [r c]
                    (for [[dr dc] dirs
                          :let [nr (+ r dr)
                                nc (+ c dc)]
                          :when (and (<= 0 nr) (< nr n) (<= 0 nc) (< nc n))]
                      [nr nc]))
        better-path (fn [p1 p2]
                      (cond
                        (nil? p1) p2
                        (nil? p2) p1
                        (neg? (compare p1 p2)) p1
                        :else p2))
        dp (loop [len 1
                  curr (into {}
                             (for [r (range n)
                                   c (range n)]
                               [[r c] [(val-at r c)]]))]
             (if (= len k)
               curr
               (let [next (into {}
                                (for [r (range n)
                                      c (range n)]
                                  (let [best (reduce
                                              (fn [acc [nr nc]]
                                                (better-path acc
                                                             (when-let [p (get curr [nr nc])]
                                                               (into [(val-at r c)] p))))
                                              nil
                                              (neighbors r c))]
                                    [[r c] best])))]
                 (recur (inc len) next))))]
    (reduce (fn [acc [_ p]] (better-path acc p)) nil dp)))