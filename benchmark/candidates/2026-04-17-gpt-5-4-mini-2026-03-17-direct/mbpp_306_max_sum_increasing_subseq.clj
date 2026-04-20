(defn max_sum_increasing_subseq
  "Write a function to find the maximum sum of increasing subsequence from prefix until ith index and also including a given kth element which is after i, i.e., k > i."
  [a n index k]
  (let [n (min n (count a))]
    (if (or (nil? a)
            (neg? index)
            (neg? k)
            (>= index n)
            (>= k n)
            (>= index k))
      nil
      (let [arr (vec a)
            prefix (subvec arr 0 (inc index))
            target (arr k)
            dp (vec
                 (map-indexed
                  (fn [i x]
                    (let [best-prev (apply max 0 (for [j (range i)
                                                       :when (< (nth prefix j) x)]
                                                   (nth dp j)))]
                      (+ x best-prev)))
                  prefix))
            best-prefix (apply max dp)]
        (if (< (arr index) target)
          (+ best-prefix target)
          nil)))))