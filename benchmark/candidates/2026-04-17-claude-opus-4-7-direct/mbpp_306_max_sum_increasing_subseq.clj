(defn max_sum_increasing_subseq
  "Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [end-index (min k (dec n))
        ;; msis stores the Maximum Sum Increasing Subsequence ending at each index i
        msis (vec (for [i (range end-index)]
                    (let [current-val (get a i)
                          ;; Calculate max sum of increasing subsequence ending at i
                          max-prev (reduce max 0
                                           (for [j (range i)
                                                 :when (< (get a j) current-val)]
                                             (get msis j)))]
                      (+ current-val max-prev))))
        ;; Find the maximum sum among all subsequences ending before or at k
        ;; that can be extended by a[k] (i.e., a[i] < a[k])
        valid-prefixes (filter #(<= % index) (range end-index))
        max-prefix-sum (reduce max 0
                               (for [i valid-prefixes
                                     :when (< (get a i) (get a k))]
                                 (get msis i)))]
    (+ max-prefix-sum (get a k))))