(defn minPath [grid k]
  (if (= k 1)
    [1]
    (let [n (count grid)
          [r c] (first (for [i (range n)
                             j (range n)
                             :when (= 1 (get-in grid [i j]))]
                         [i j]))
          dirs [[-1 0] [1 0] [0 -1] [0 1]]
          neighbors (for [[dr dc] dirs
                          :let [nr (+ r dr) nc (+ c dc)]
                          :when (and (>= nr 0) (< nr n) (>= nc 0) (< nc n))]
                      (get-in grid [nr nc]))
          min-neighbor (apply min neighbors)]
      (vec (take k (cycle [1 min-neighbor]))))))