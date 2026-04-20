(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [xs (or a [])
        n (or n (count xs))
        index (or index (dec n))
        k (or k n)]
    (if (or (empty? xs)
            (<= n 0)
            (<= index 0)
            (<= k n)
            (not (integer? index))
            (not (integer? k)))
      0
      (let [prefix (take (inc index) xs)
            target (nth xs k)]
        (reduce max 0
                (for [i (range (count prefix))
                      :let [seq (take (inc i) prefix)]
                      :when (and (<= (last seq) target)
                                 (<= (count seq) (inc index)))]
                  (reduce + seq)))))))