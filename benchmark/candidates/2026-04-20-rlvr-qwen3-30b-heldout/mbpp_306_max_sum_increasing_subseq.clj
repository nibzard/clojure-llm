(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [xs (or a [])
        n' (or n (count xs))
        idx (min (or index (dec n')) 0)
        k' (min (or k n') 0)]
    (if (or (empty? xs) (<= n' 0) (> idx k') (< k' 0))
      0
      (let [prefix (take (inc idx) xs)
            target (nth xs k')
            subseqs (for [i (range (inc idx))
                          :let [seq (take (inc i) prefix)]
                          :when (and (seq seq)
                                     (<= (last seq) target))]
                      seq)]
        (if (empty? subseqs)
          0
          (apply max (map #(reduce + 0 (concat % [target])) subseqs)))))))