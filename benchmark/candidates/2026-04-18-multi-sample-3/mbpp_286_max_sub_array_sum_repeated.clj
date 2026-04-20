(defn max_sub_array_sum_repeated
  "	Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [v (vec (or a []))
        n' (min (or n 0) (count v))
        xs (subvec v 0 n')]
    (if (or (zero? n') (<= (or k 0) 0))
      0
      (let [kadane (fn [coll]
                     (reduce (fn [[best curr] x]
                               (let [curr' (max x (+ curr x))]
                                 [(max best curr') curr']))
                             [Long/MIN_VALUE 0]
                             coll))
            total-sum (reduce + 0 xs)
            max-prefix (reduce (fn [[best curr] x]
                                 (let [curr' (+ curr x)]
                                   [(max best curr') curr']))
                               [Long/MIN_VALUE 0]
                               xs)
            max-suffix (reduce (fn [[best curr] x]
                                 (let [curr' (+ curr x)]
                                   [(max best curr') curr']))
                               [Long/MIN_VALUE 0]
                               (rseq xs))
            one-copy-best (first (kadane xs))]
        (if (= k 1)
          one-copy-best
          (let [two-copy-best (first (kadane (concat xs xs)))
                cross-best (+ (first max-prefix) (first max-suffix))]
            (if (pos? total-sum)
              (max two-copy-best (+ cross-best (* (- k 2) total-sum)))
              two-copy-best)))))))