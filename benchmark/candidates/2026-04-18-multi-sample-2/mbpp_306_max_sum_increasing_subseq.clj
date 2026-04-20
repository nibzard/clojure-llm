(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        len (min (or n 0) (count v))]
    (if (or (neg? index)
            (neg? k)
            (>= index len)
            (>= k len)
            (<= k index))
      0
      (let [vk (nth v k)
            dp (reduce
                (fn [dp i]
                  (let [vi (nth v i)
                        best-prev (reduce
                                   (fn [best j]
                                     (if (< (nth v j) vi)
                                       (max best (nth dp j))
                                       best))
                                   0
                                   (range i))]
                    (conj dp (+ vi best-prev))))
                []
                (range (inc index)))]
        (reduce
         (fn [best i]
           (let [vi (nth v i)]
             (if (< vi vk)
               (max best (+ (nth dp i) vk))
               best)))
         0
         (range (inc index)))))))