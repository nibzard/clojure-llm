(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        nums (subvec v 0 (inc (max n index k)))
        i (max 0 (min index (dec (count nums))))
        k (max (inc i) (min k (dec (count nums))))]
    (if (or (>= i k) (>= k (count nums)))
      0
      (let [dp (vec (repeat (inc (count nums)) 0))]
        (loop [j 0]
          (if (> j k)
            (nth dp (inc k))
            (let [x (nth nums j)]
              (recur (inc j)))))))))