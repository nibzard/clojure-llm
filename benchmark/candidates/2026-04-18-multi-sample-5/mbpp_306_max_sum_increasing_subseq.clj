(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        cnt (count v)
        n* (max 0 (min (or n 0) cnt))
        i (or index -1)
        k* (or k -1)]
    (if (or (neg? i)
            (neg? k*)
            (>= i n*)
            (>= k* n*)
            (<= k* i))
      0
      (let [ak (nth v k*)
            dp (reduce
                (fn [dp j]
                  (let [aj (nth v j)]
                    (assoc dp j
                           (if (> aj ak)
                             Long/MIN_VALUE
                             (let [best-prev (reduce
                                              (fn [best p]
                                                (let [ap (nth v p)]
                                                  (if (and (< ap aj)
                                                           (not= (nth dp p) Long/MIN_VALUE))
                                                    (max best (nth dp p))
                                                    best)))
                                              0
                                              (range 0 j))]
                               (+ aj best-prev))))))
                (vec (repeat (inc i) 0))
                (range 0 (inc i)))
            best (reduce
                  (fn [m j]
                    (let [aj (nth v j)
                          dj (nth dp j)]
                      (if (and (< aj ak) (not= dj Long/MIN_VALUE))
                        (max m dj)
                        m)))
                  0
                  (range 0 (inc i)))]
        (+ best ak)))))