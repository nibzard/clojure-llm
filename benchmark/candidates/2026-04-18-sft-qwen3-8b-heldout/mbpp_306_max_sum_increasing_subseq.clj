(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [xs (vec (or a []))
        i (max 0 (min index (dec n)))
        k (max (inc i) (min k (dec n)))]
    (if (or (neg? i) (neg? k) (>= i k))
      nil
      (let [prefix (subvec xs 0 (inc i))
            suffix (subvec xs (inc i) (inc k))
            dp (reduce (fn [bests x]
                         (assoc bests x (reduce max 0
                                                (map #(if (< % x) (+ % (bests %)) 0)
                                                     (range x)))))
                       {}
                       prefix)]
        (reduce max 0
                (map #(if (< % (xs k)) (+ % (dp %)) 0)
                     (range (xs k))))))))