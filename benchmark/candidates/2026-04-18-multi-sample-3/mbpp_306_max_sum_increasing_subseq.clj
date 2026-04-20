(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        len (count v)
        n (min (max (or n 0) 0) len)]
    (if (or (zero? n)
            (neg? index)
            (neg? k)
            (>= index n)
            (>= k n)
            (<= k index))
      0
      (let [target (v k)
            dp (reduce
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
                (vec (repeat n 0))
                (range n))
            best-prefix (reduce
                         (fn [best i]
                           (if (< (v i) target)
                             (max best (dp i))
                             best))
                         0
                         (range (inc index)))]
        (+ target best-prefix)))))