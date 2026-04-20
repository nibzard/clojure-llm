(defn max_sum_increasing_subseq
  "	Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i ."
  [a n index k]
  (let [v (vec (or a []))
        nums (subvec v 0 (inc (max n index k)))
        best (reduce (fn [best i]
                       (let [x (nth nums i)
                             prev-best (subvec best 0 i)]
                         (assoc best i (reduce max 0 (map (fn [j]
                                                           (if (> (nth nums j) (nth nums i))
                                                             (+ (nth best j) x)
                                                             0))
                                                         (range i)))))))
                 (vec (repeat (count nums) 0))
                 (range (count nums)))]
    (if (and (<= index k)
             (< index (count nums))
             (< k (count nums)))
      (+ (nth nums k) (nth best index))
      0))