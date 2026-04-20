(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        cnt (min (max (or n 0) 0) (count v))]
    (if (or (zero? cnt)
            (neg? index)
            (neg? k)
            (>= index cnt)
            (>= k cnt)
            (<= k index))
      0
      (let [prefix (subvec v 0 (inc index))
            target (v k)
            msis (reduce
                  (fn [dp i]
                    (let [xi (prefix i)
                          best (reduce
                                (fn [m j]
                                  (if (< (prefix j) xi)
                                    (max m (dp j))
                                    m))
                                0
                                (range i))]
                      (conj dp (+ xi best))))
                  []
                  (range (count prefix)))
            best-prefix (reduce
                         (fn [m i]
                           (if (< (prefix i) target)
                             (max m (msis i))
                             m))
                         0
                         (range (count prefix)))]
        (+ target best-prefix)))))