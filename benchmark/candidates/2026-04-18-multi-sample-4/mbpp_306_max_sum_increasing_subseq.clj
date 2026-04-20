(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        cnt (min (max 0 (or n 0)) (count v))
        i (or index -1)
        kk (or k -1)]
    (if (or (neg? i)
            (neg? kk)
            (>= i cnt)
            (>= kk cnt)
            (<= kk i))
      0
      (let [target (v kk)
            dp (reduce
                (fn [dp j]
                  (let [x (v j)
                        best-prev (reduce
                                   (fn [m p]
                                     (if (< (v p) x)
                                       (max m (dp p))
                                       m))
                                   0
                                   (range j))]
                    (assoc dp j (+ x best-prev))))
                (vec (repeat cnt 0))
                (range cnt))
            best-prefix (reduce
                         (fn [m j]
                           (if (< (v j) target)
                             (max m (dp j))
                             m))
                         0
                         (range (inc i)))]
        (+ target best-prefix)))))