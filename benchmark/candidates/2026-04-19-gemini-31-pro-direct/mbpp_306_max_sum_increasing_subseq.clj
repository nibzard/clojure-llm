(defn max_sum_increasing_subseq
  [a n index k]
  (let [dp (reduce (fn [dp-acc j]
                     (let [aj (nth a j)
                           prev-max (reduce max 0
                                            (for [x (range j)
                                                  :when (< (nth a x) aj)]
                                              (nth dp-acc x)))]
                       (conj dp-acc (+ prev-max aj))))
                   []
                   (range (inc index)))
        ak (nth a k)
        valid-dp (reduce max 0
                         (for [x (range (inc index))
                               :when (< (nth a x) ak)]
                           (nth dp x)))]
    (+ valid-dp ak)))