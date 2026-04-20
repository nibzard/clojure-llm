(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        cnt (count v)
        n* (min (max 0 (or n 0)) cnt)
        idx (or index -1)
        k* (or k -1)]
    (if (or (zero? n*)
            (neg? idx)
            (neg? k*)
            (>= idx n*)
            (>= k* n*)
            (<= k* idx))
      0
      (let [target (v k*)
            init-dp (vec (repeat (inc idx) nil))
            dp (reduce
                (fn [dp i]
                  (let [x (v i)
                        best-prev (reduce
                                   (fn [best j]
                                     (let [dj (dp j)]
                                       (if (and dj (< (v j) x))
                                         (max best dj)
                                         best)))
                                   0
                                   (range i))
                        base (+ x best-prev)
                        val (if (< x target) base nil)]
                    (assoc dp i val)))
                init-dp
                (range (inc idx)))
            best-prefix (reduce
                         (fn [best i]
                           (let [di (dp i)]
                             (if di (max best di) best)))
                         0
                         (range (inc idx)))]
        (+ target best-prefix)))))