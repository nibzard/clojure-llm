(defn max_sum
  [arr]
  (if (empty? arr)
    0
    (let [n (count arr)
          arr (vec arr)
          incis (reduce (fn [dp i]
                          (let [val (arr i)
                                prevs (for [j (range i)
                                            :when (< (arr j) val)]
                                        (dp j))
                                max-prev (if (empty? prevs) 0 (reduce max prevs))]
                            (assoc dp i (+ val max-prev))))
                        (vec (repeat n 0))
                        (range n))
          decis (reduce (fn [dp i]
                          (let [val (arr i)
                                nexts (for [j (range (inc i) n)
                                            :when (< (arr j) val)]
                                        (dp j))
                                max-next (if (empty? nexts) 0 (reduce max nexts))]
                            (assoc dp i (+ val max-next))))
                        (vec (repeat n 0))
                        (reverse (range n)))]
      (reduce max (map (fn [i] (- (+ (incis i) (decis i)) (arr i)))
                       (range n))))))