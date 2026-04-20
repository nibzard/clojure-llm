(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        len (count v)]
    (if (or (neg? index)
            (neg? k)
            (>= index len)
            (>= k len)
            (> (inc index) n)
            (<= n 0)
            (<= k index))
      0
      (let [limit (min n len)
            kth   (v k)]
        (if (<= kth (v index))
          0
          (let [dp (reduce
                     (fn [dp i]
                       (let [vi (v i)
                             best-prev (reduce
                                         (fn [best j]
                                           (if (< (v j) vi)
                                             (max best (dp j))
                                             best))
                                         0
                                         (range i))]
                         (assoc dp i (+ vi best-prev))))
                     (vec (repeat limit 0))
                     (range limit))]
            (reduce
              (fn [best i]
                (if (and (<= i index) (< (v i) kth))
                  (max best (+ (dp i) kth))
                  best))
              0
              (range limit)))))))